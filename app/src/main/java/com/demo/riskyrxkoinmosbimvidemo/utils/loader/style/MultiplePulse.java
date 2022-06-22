package com.demo.riskyrxkoinmosbimvidemo.utils.loader.style;

import com.demo.riskyrxkoinmosbimvidemo.utils.loader.sprite.Sprite;
import com.demo.riskyrxkoinmosbimvidemo.utils.loader.sprite.SpriteContainer;

public class MultiplePulse extends SpriteContainer {
    @Override
    public Sprite[] onCreateChild() {
        return new Sprite[]{
                new Pulse(),
                new Pulse(),
                new Pulse(),
        };
    }

    @Override
    public void onChildCreated(Sprite... sprites) {
        for (int i = 0; i < sprites.length; i++) {
            sprites[i].setAnimationDelay(200 * (i + 1));
        }
    }
}
