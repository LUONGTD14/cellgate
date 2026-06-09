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
import android.os.Handler;
import android.os.Looper;
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

  private final Context context;
  private final float scrollSpeed = 4f;
  private final Handler mainHandler = new Handler(Looper.getMainLooper());
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
        e.printStackTrace();
      }
      gameLoop = null;
    }
  }

  public void resume() {
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
    RectF planeRect = plane.getBounds();
    for (Wall wall : mapData.getWalls()) {
      RectF r = new RectF(wall.getRect());
      r.offset(0, scrollY);
      if (RectF.intersects(planeRect, r)) {
        return true;
      }
    }
    return false;
  }

  private void drawGame() {
    Canvas canvas = getHolder().lockCanvas();
    if (canvas == null) return;
    try {
      synchronized (getHolder()) {
        backgroundRenderer.draw(canvas, getWidth(), getHeight());
        particleSystem.draw(canvas, particlePaint);
        drawWalls(canvas);
        drawPlane(canvas);
        hudRenderer.draw(canvas, scoreManager.getScore());
      }
    } finally {
      getHolder().unlockCanvasAndPost(canvas);
    }
  }

  private void drawWalls(Canvas canvas) {
    canvas.save();
    canvas.translate(0, scrollY);
    for (Wall wall : mapData.getWalls()) {
      canvas.drawRoundRect(wall.getRect(), 12, 12, wallPaint);
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
    }
    return true;
  }

  @Override
  public void surfaceCreated(@NonNull SurfaceHolder holder) {
    initializeGame();
    if (gameLoop == null) {
      gameLoop =
          new GameLoop(
              () -> {
                update();
                drawGame();
              });
      gameLoop.startLoop();
    }
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
}
