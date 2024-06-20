package com.zyy;

import java.awt.*;

/**
 * @Author yyzhou
 * @Date 2024/6/19 23:14
 * @PackageName:com.zyy
 * @ClassName: Wall
 * @Description: TODO
 * @Version 1.0
 */
public class Wall extends GameObject{
    //
    int length=50;
    public Wall(String img, int x, int y, GamePanel gamePanel) {
        super(img, x, y, gamePanel);
    }

    public void paintSelf(Graphics g) {
        g.drawImage(img,x,y,null);
    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(x,y,length,length);
    }
}
