package com.TillDawn.Model;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class BossRect {
    float x, y;
    float width, height;

    public BossRect(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void update(float delta) {
        x += delta;
        y += delta;
        width -= 2 * delta;
        height -= 2 * delta;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth(){
        return width;
    }

    public float getHeight(){
        return height;
    }

    public boolean intersects(CollisionRect rect) {

        float x1 = x, y1 = y, w1 = width, h1 = height;
        float x2 = rect.x, y2 = rect.y, w2 = rect.width, h2 = rect.height;
        float thickness = 2f;

        boolean topCollision =
            y2 + h2 > y1 + h1 - thickness &&
                y2 < y1 + h1 &&
                x2 + w2 > x1 &&
                x2 < x1 + w1;

        boolean bottomCollision =
            y2 < y1 + thickness &&
                y2 + h2 > y1 &&
                x2 + w2 > x1 &&
                x2 < x1 + w1;

        boolean leftCollision =
            x2 < x1 + thickness &&
                x2 + w2 > x1 &&
                y2 + h2 > y1 &&
                y2 < y1 + h1;

        boolean rightCollision =
            x2 + w2 > x1 + w1 - thickness &&
                x2 < x1 + w1 &&
                y2 + h2 > y1 &&
                y2 < y1 + h1;
        return topCollision || bottomCollision || leftCollision || rightCollision;

    }
}
