package com.ltd14.cellgate.game;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import com.ltd14.cellgate.R;
import com.ltd14.cellgate.activities.GameActivity;
import com.ltd14.cellgate.activities.GameOverActivity;
import com.ltd14.cellgate.generator.MapGenerator;
import com.ltd14.cellgate.model.MapData;
import com.ltd14.cellgate.model.Plane;
import com.ltd14.cellgate.model.Wall;
import com.ltd14.cellgate.sound.SoundManager;
import com.ltd14.cellgate.ui.BackgroundRenderer;
import com.ltd14.cellgate.ui.HudRenderer;
import com.ltd14.cellgate.ui.ParticleSystem;
import com.ltd14.cellgate.util.Constants;
import com.ltd14.cellgate.util.PreferenceUtil;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

  private static final String TAG = "GameView";
  private final Context context;
  private final float scrollSpeed = 4f;
  private final Handler mainHandler = new Handler(Looper.getMainLooper());
  private final RectF collisionTmpRect = new RectF();
  private GameLoop gameLoop;
  private Plane plane;
  private MapData mapData;
  private MapGenerator mapGenerator;
  private ScoreManager scoreManager;
  private SoundManager soundManager;
  private BackgroundRenderer backgroundRenderer;
  private ParticleSystem particleSystem;
  private HudRenderer hudRenderer;
  private Paint wallPaint;
  private Paint particlePaint;
  private Bitmap planeBitmap;
  private GameState state = GameState.READY;
  private boolean initialized;
  private float scrollY;

  public GameView(Context context) {
    super(context);
    this.context = context;
    getHolder().addCallback(this);
    init();
  }

  private void init() {
    scoreManager = new ScoreManager();
    mapGenerator = new MapGenerator();
    backgroundRenderer = new BackgroundRenderer();
    hudRenderer = new HudRenderer();
    soundManager = new SoundManager(context);

    wallPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    wallPaint.setColor(Color.WHITE);
    particlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    particlePaint.setColor(0x55FFFFFF);

    Drawable drawable = AppCompatResources.getDrawable(context, R.drawable.airplane);
    int desiredWidth = (int) Constants.PLANE_WIDTH;
    int desiredHeight = (int) Constants.PLANE_HEIGHT;
    if (drawable != null) {
      planeBitmap = Bitmap.createBitmap(desiredWidth, desiredHeight, Bitmap.Config.ARGB_8888);
      Canvas canvas = new Canvas(planeBitmap);
      drawable.setBounds(0, 0, desiredWidth, desiredHeight);
      drawable.draw(canvas);
    } else {
      planeBitmap = null;
    }
  }

  private void initializeGame() {
    if (initialized) return;
    initialized = true;

    plane =
        new Plane(
            getWidth() / 2f,
            (float) (getHeight() - getHeight() * 0.4),
            Constants.PLANE_WIDTH,
            Constants.PLANE_HEIGHT);

    particleSystem = new ParticleSystem(getWidth(), getHeight());
    loadNextMap();
    state = GameState.PLAYING;
  }

  private void loadNextMap() {
    mapData = mapGenerator.generate(getWidth(), getHeight(), scoreManager.getScore());
    scrollY = -mapData.getMapHeight();
    plane.setPosition(getWidth() / 2f, (float) (getHeight() - getHeight() * 0.4));
  }

  public void pause() {
    if (gameLoop != null) {
      gameLoop.stopLoop();
      try {
        gameLoop.join();
      } catch (InterruptedException e) {
        Log.e(TAG, "Error joining game loop", e);
      }
      gameLoop = null;
    }
  }

  public void resume() {
    if (gameLoop == null && getHolder().getSurface().isValid()) {
      gameLoop =
          new GameLoop(
              () -> {
                update();
                drawGame();
              });
      gameLoop.startLoop();
    }
  }

  private void update() {
    if (state != GameState.PLAYING) return;
    scrollY += scrollSpeed;
    plane.update();
    particleSystem.update(getWidth(), getHeight());

    if (checkCollision()) {
      gameOver();
      return;
    }

    if (scrollY >= mapData.getMapHeight()) {
      scoreManager.increase();
      soundManager.playSuccess();
      loadNextMap();
    }
  }

  private boolean checkCollision() {
    if (plane == null || mapData == null) return false;
    RectF planeRect = plane.getBounds();
    for (Wall wall : mapData.getWalls()) {
      collisionTmpRect.set(wall.getRect());
      collisionTmpRect.offset(0, scrollY);

      // Culling optimization: skip collision if wall is outside screen vertically
      if (collisionTmpRect.bottom < 0 || collisionTmpRect.top > getHeight()) {
        continue;
      }

      if (RectF.intersects(planeRect, collisionTmpRect)) {
        return true;
      }
    }
    return false;
  }

  private void drawGame() {
    SurfaceHolder holder = getHolder();
    Canvas canvas = null;
    try {
      // Use hardware acceleration for API 26+
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        canvas = holder.lockHardwareCanvas();
      } else {
        canvas = holder.lockCanvas();
      }

      if (canvas != null) {
        synchronized (holder) {
          backgroundRenderer.draw(canvas, getWidth(), getHeight());
          particleSystem.draw(canvas, particlePaint);
          drawWalls(canvas);
          drawPlane(canvas);
          hudRenderer.draw(canvas, scoreManager.getScore());
        }
      }
    } catch (Exception e) {
      Log.e(TAG, "Drawing error in drawGame", e);
    } finally {
      if (canvas != null) {
        try {
          holder.unlockCanvasAndPost(canvas);
        } catch (Exception e) {
          Log.e(TAG, "Error unlocking canvas", e);
        }
      }
    }
  }

  private void drawWalls(Canvas canvas) {
    canvas.save();
    canvas.translate(0, scrollY);
    for (Wall wall : mapData.getWalls()) {
      RectF r = wall.getRect();
      // Optimization: don't draw walls that are off-screen
      if (r.top + scrollY > getHeight() || r.bottom + scrollY < 0) {
        continue;
      }
      canvas.drawRoundRect(r, 12, 12, wallPaint);
    }
    canvas.restore();
  }

  private void drawPlane(Canvas canvas) {
    if (planeBitmap == null) {
      float x = plane.getX();
      float y = plane.getY();
      Paint p = new Paint();
      p.setColor(0xFFFFC107);
      p.setAntiAlias(true);
      Path path = new Path();
      path.moveTo(x, y - 20f);
      path.lineTo(x - 15f, y + 10f);
      path.lineTo(x + 15f, y + 10f);
      path.close();
      canvas.drawPath(path, p);
      return;
    }

    float x = plane.getX();
    float y = plane.getY();
    float angle = plane.getAngle();
    canvas.save();
    canvas.translate(x, y);
    canvas.rotate(angle);
    canvas.drawBitmap(
        planeBitmap, -planeBitmap.getWidth() / 2f, -planeBitmap.getHeight() / 2f, null);
    canvas.restore();
  }

  private void gameOver() {
    if (state == GameState.GAME_OVER) return;
    state = GameState.GAME_OVER;
    soundManager.playFail();
    int score = scoreManager.getScore();
    int best = PreferenceUtil.getBestScore(context);
    if (score > best) {
      PreferenceUtil.saveBestScore(context, score);
    }

    mainHandler.postDelayed(
        () -> {
          Intent intent = new Intent(context, GameOverActivity.class);
          intent.putExtra("score", score);
          intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
          context.startActivity(intent);
          if (context instanceof GameActivity) {
            ((GameActivity) context).finish();
          }
        },
        500);
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    if (plane == null) return true;
    switch (event.getAction()) {
      case MotionEvent.ACTION_DOWN:
      case MotionEvent.ACTION_MOVE:
        plane.setTargetX(event.getX());
        break;
      case MotionEvent.ACTION_UP:
        performClick();
        break;
    }
    return true;
  }

  @Override
  public boolean performClick() {
    return super.performClick();
  }

  @Override
  public void surfaceCreated(@NonNull SurfaceHolder holder) {
    initializeGame();
    resume();
  }

  @Override
  public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {}

  @Override
  public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
    pause();
  }

  @Override
  protected void onDetachedFromWindow() {
    super.onDetachedFromWindow();
    mainHandler.removeCallbacksAndMessages(null);
    if (soundManager != null) {
      soundManager.release();
    }
  }

  @Override
  protected void onAttachedToWindow() {
    super.onAttachedToWindow();
    if (soundManager == null) {
      soundManager = new SoundManager(context);
    }
  }
}
