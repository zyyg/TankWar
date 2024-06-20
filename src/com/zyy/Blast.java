package com.zyy;

import java.awt.*;

/**
 * @Author yyzhou
 * @Date 2024/6/20 10:02
 * @PackageName:com.zyy
 * @ClassName: Blast
 * @Description: TODO
 * @Version 1.0
 */
public class Blast extends GameObject{

    static Image[] imgs=new Image[8];
    int explodeCount=0;
    static {
        for (int i = 0; i < 8; i++) {
            String filename="images/blast/blast"+(i+1)+".gif";
//            System.out.println(filename);
            imgs[i]=Toolkit.getDefaultToolkit().getImage("images/blast/blast"+(i+1)+".gif");
        }
    }
    public Blast(String img, int x, int y, GamePanel gamePanel) {
        super(img, x, y, gamePanel);
    }

    @Override
    public void paintSelf(Graphics g) {
        if(explodeCount<8){
            g.drawImage(imgs[explodeCount],x,y,null );
            explodeCount++;
        }
    }

    @Override
    public Rectangle getRec() {
        return null;
    }
}
