package com.TillDawn.Model;

public class Timer {
    private float elapsedTime = 0f;
    private float duration;

    public Timer(float duration) {
        this.duration = duration;
    }

    public void update(float delta) {
        elapsedTime += delta;
    }

    public boolean isFinished() {
        return elapsedTime >= duration;
    }

    public float getTimeLeft() {
        return Math.max(duration - elapsedTime, 0);
    }

    public void reset() {
        elapsedTime = 0f;
    }
}
