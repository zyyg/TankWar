package com.zyy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

/**
 * @Author yyzhou
 * @Date 2024/6/18 17:51
 * @PackageName:com.zyy
 * @ClassName: GamePanel
 * @Description: TODO
 * @Version 1.0
 */
public class GamePanel extends JFrame {

    //定义双缓存图片
    Image offScreenImage=null;
    //定义窗口尺寸
    int width=800;
    int height=610;
    Image select = Toolkit.getDefaultToolkit().getImage("images/selectTank.gif");
    int y=150;

    int count=0;
    int enemyCount=0;
    ArrayList<Bullet> bulletList=new ArrayList<Bullet>();
    ArrayList<Bot> botList=new ArrayList<Bot>();

    ArrayList<Bullet> removeList=new ArrayList<Bullet>();
    ArrayList<Tank> playerList=new ArrayList<Tank>();

    ArrayList<Wall> wallList=new ArrayList<Wall>();//围墙列表

    ArrayList<Base> baseList=new ArrayList<Base>();//基地列表

    ArrayList<Blast> blastList=new ArrayList<>();//爆炸元素列表

    PlayerOne playerOne=new PlayerOne("images/player1/p1tankU.gif",125,510,this,
            "images/player1/p1tankU.gif","images/player1/p1tankL.gif",
            "images/player1/p1tankR.gif","images/player1/p1tankD.gif");
    PlayerTwo playerTwo=new PlayerTwo("images/player2/p2tankU.gif",625,510,this,
            "images/player2/p2tankU.gif","images/player2/p2tankL.gif",
            "images/player2/p2tankR.gif","images/player2/p2tankD.gif");
    Base base=new Base("images/star.gif",375,570,this);

    int a=1;
    //游戏模式 0游戏未开始，1单人模式，2双人模式 5游戏胜利,4游戏失败
    int state=0;
    /**
     * 窗口启动方法
     */
    public void launch(){
        //设置标题
        setTitle("zyy坦克大战");
        //窗口初始化大小

        setSize(width,height);
        setLocationRelativeTo(null);//使屏幕居中
        setDefaultCloseOperation(3);//添加关闭事件
        setResizable(false);//用户不能调整大小
        setVisible(true);
        this.addKeyListener(new GamePanel.KeyMonitor());

        //添加围墙
        for (int i = 0; i < 14; i++) {
            wallList.add(new Wall("images/walls.gif",i*60,170,this));
        }
        wallList.add(new Wall("images/walls.gif",305,560,this));
        wallList.add(new Wall("images/walls.gif",305,500,this));
        wallList.add(new Wall("images/walls.gif",365,500,this));
        wallList.add(new Wall("images/walls.gif",425,500,this));
        wallList.add(new Wall("images/walls.gif",425,560,this));
        baseList.add(base);
        //重绘
        while (true){
            //游戏胜利判断
            if(botList.size()==0&&enemyCount==10){
                state=5;
            }
            if((playerList.size()==0&&(state==1||state==2))||baseList.size()==0){
                state=4;
            }
            if(count%100==1&enemyCount<10&&(state==1||state==2)){
                Random random=new Random();
                int xnum= random.nextInt(800);
                botList.add(new Bot("images/enemy/enemy1U.gif",xnum,110,this,
                        "images/enemy/enemy1U.gif","images/enemy/enemy1L.gif",
                        "images/enemy/enemy1R.gif","images/enemy/enemy1D.gif"));
                enemyCount++;
            }

            repaint();
            try {
                Thread.sleep(25);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void paint(Graphics g){

        //创建和窗口一样大小的图片
        if(offScreenImage==null){
            offScreenImage=this.createImage(width,height);
        }
        //获取图片的画笔
        Graphics gImages = offScreenImage.getGraphics();

        //设置画笔颜色
        gImages.setColor(Color.gray);
        gImages.fillRect(0,0,width,height);
        //添加文字
        gImages.setColor(Color.blue);
        gImages.setFont(new Font("仿宋",Font.BOLD,50));
        if(state==0){
            gImages.drawString("选择游戏模式",220,100);
            gImages.drawString("单人模式",220,200);
            gImages.drawString("双人模式",220,300);
            gImages.drawImage(select,160,y,null);
        }else if(state==1||state==2){
            gImages.setFont(new Font("仿宋",Font.BOLD,30));
            gImages.setColor(Color.red);
            gImages.drawString("剩余敌人："+botList.size(),0,50);
            if(state==1){
                gImages.drawString("单人模式",220,200);
            }
            else{
                gImages.drawString("双人模式",220,200);
            }
            //添加游戏元素
            for (Tank player:playerList) {
                player.paintSelf(gImages);
            }
//            playerOne.paintSelf(gImages);
            for (Bullet bullet:bulletList) {
                bullet.paintSelf(gImages);
            }
            bulletList.removeAll(removeList);
            for (Bot bot:botList){
                bot.paintSelf(gImages);
            }
            for (Wall wall:wallList) {
                wall.paintSelf(gImages);

            }
            for(Base base:baseList){
                base.paintSelf(gImages);
            }
            for(Blast blast:blastList){
                blast.paintSelf(gImages);
            }
            count++;
        }
        else if(state==5){
            gImages.drawString("游戏胜利",220,200);
        }
        else if(state==4){
            gImages.drawString("游戏失败",220,200);
        }
        else if(state==3){
            gImages.drawString("游戏暂停",220,200);
        }
        //将缓存区的绘制好的图形整个绘制到容器的画布中
        g.drawImage(offScreenImage,0,0,null);

    }

    /**
     * 键盘监听时间
     */
    class KeyMonitor extends KeyAdapter{
        public void keyPressed(KeyEvent e){
            int key=e.getKeyCode();
            switch (key){
                case KeyEvent.VK_1:
                    a=1;
                    y=150;
                    break;
                case KeyEvent.VK_2:
                    a=2;
                    y=250;
                    break;
                case KeyEvent.VK_ENTER:
                    state=a;
                    playerList.add(playerOne);
                    if(state==2){
                        playerList.add(playerTwo);
                        playerTwo.alive=true;
                    }
                    playerOne.alive=true;

                    break;
                case KeyEvent.VK_P:
                    if(state!=3){
                        a=state;
                        state=3;
                    }else {
                        state=a;
                        if(a==0){
                            a=1;
                        }
                    }
                default:
                    playerOne.keyPressed(e);
                    playerTwo.keyPressed(e);
            }
//            System.out.println(e.getKeyChar());
        }

        public void keyReleased(KeyEvent e){
            playerOne.keyReleased(e);
            playerTwo.keyReleased(e);
//            System.out.println(e.getKeyChar());
        }
    }

    public static void main(String[] args) {
        GamePanel gamePanel=new GamePanel();
        gamePanel.launch();
    }
}
