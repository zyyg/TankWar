package com.zyy;

import java.awt.*;

/**
 * @Author yyzhou
 * @Date 2024/6/19 14:46
 * @PackageName:com.zyy
 * @ClassName: GameObject
 * @Description: TODO
 * @Version 1.0
 */
public abstract class GameObject {
    //图片
    public Image img;
    //坐标
    public int x;
    public int y;
    //界面
    public GamePanel gamePanel;

    public GameObject(String img,int x,int y,GamePanel gamePanel){
        this.img=Toolkit.getDefaultToolkit().getImage(img);
        this.x=x;
        this.y=y;
        this.gamePanel=gamePanel;
    }

    public abstract void paintSelf(Graphics g);

    public abstract Rectangle getRec();
}
