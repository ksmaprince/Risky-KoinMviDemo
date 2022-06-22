package com.demo.riskyrxkoinmosbimvidemo.utils.loader;

import com.demo.riskyrxkoinmosbimvidemo.utils.loader.sprite.Sprite;
import com.demo.riskyrxkoinmosbimvidemo.utils.loader.style.ChasingDots;
import com.demo.riskyrxkoinmosbimvidemo.utils.loader.style.Circle;
import com.demo.riskyrxkoinmosbimvidemo.utils.loader.style.CubeGrid;
import com.demo.riskyrxkoinmosbimvidemo.utils.loader.style.DoubleBounce;
import com.demo.riskyrxkoinmosbimvidemo.utils.loader.style.FadingCircle;
import com.demo.riskyrxkoinmosbimvidemo.utils.loader.style.FoldingCube;
import com.demo.riskyrxkoinmosbimvidemo.utils.loader.style.MultiplePulse;
import com.demo.riskyrxkoinmosbimvidemo.utils.loader.style.MultiplePulseRing;
import com.demo.riskyrxkoinmosbimvidemo.utils.loader.style.Pulse;
import com.demo.riskyrxkoinmosbimvidemo.utils.loader.style.PulseRing;
import com.demo.riskyrxkoinmosbimvidemo.utils.loader.style.RotatingCircle;
import com.demo.riskyrxkoinmosbimvidemo.utils.loader.style.RotatingPlane;
import com.demo.riskyrxkoinmosbimvidemo.utils.loader.style.ThreeBounce;
import com.demo.riskyrxkoinmosbimvidemo.utils.loader.style.WanderingCubes;
import com.demo.riskyrxkoinmosbimvidemo.utils.loader.style.Wave;

public class SpriteFactory {

    public static Sprite create(Style style) {
        Sprite sprite = null;
        switch (style) {
            case ROTATING_PLANE:
                sprite = new RotatingPlane();
                break;
            case DOUBLE_BOUNCE:
                sprite = new DoubleBounce();
                break;
            case WAVE:
                sprite = new Wave();
                break;
            case WANDERING_CUBES:
                sprite = new WanderingCubes();
                break;
            case PULSE:
                sprite = new Pulse();
                break;
            case CHASING_DOTS:
                sprite = new ChasingDots();
                break;
            case THREE_BOUNCE:
                sprite = new ThreeBounce();
                break;
            case CIRCLE:
                sprite = new Circle();
                break;
            case CUBE_GRID:
                sprite = new CubeGrid();
                break;
            case FADING_CIRCLE:
                sprite = new FadingCircle();
                break;
            case FOLDING_CUBE:
                sprite = new FoldingCube();
                break;
            case ROTATING_CIRCLE:
                sprite = new RotatingCircle();
                break;
            case MULTIPLE_PULSE:
                sprite = new MultiplePulse();
                break;
            case PULSE_RING:
                sprite = new PulseRing();
                break;
            case MULTIPLE_PULSE_RING:
                sprite = new MultiplePulseRing();
                break;
            default:
                break;
        }
        return sprite;
    }
}
