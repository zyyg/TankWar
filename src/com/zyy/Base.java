package com.zyy;

import java.awt.*;

/**
 * @Author yyzhou
 * @Date 2024/6/20 0:00
 * @PackageName:com.zyy
 * @ClassName: Base
 * @Description: 游戏基地类
 * @Version 1.0
 */

public class Base extends GameObject{
    int length=50;

    public Base(String img, int x, int y, GamePanel gamePanel) {
        super(img, x, y, gamePanel);
    }

    @Override
    public void paintSelf(Graphics g) {
        g.drawImage(img,x,y,null);
    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(x,y,length,length);
    }
}
