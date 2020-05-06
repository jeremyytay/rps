package com.xp.rps;

public class Round {
    private Throw throw1;
    private Throw throw2;
    private Result result;

    public Round() {
    }

    public Round(Throw throw1, Throw throw2) {
        this.throw1 = throw1;
        this.throw2 = throw2;
    }

    public Throw getThrow1() {
        return throw1;
    }

    public void setThrow1(Throw throw1) {
        this.throw1 = throw1;
    }

    public Throw getThrow2() {
        return throw2;
    }

    public void setThrow2(Throw throw2) {
        this.throw2 = throw2;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
