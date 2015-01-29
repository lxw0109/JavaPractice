import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JOptionPane;

//俄罗斯方块类 
public class Tetris extends Frame {
	private static final long serialVersionUID = 1L;

	static final int ROW_NUM = 15;
	static final int COL_NUM = 10;

	public static boolean isPlay = false;
	public static int level = 1, score = 0;
	public static TextField scoreField, levelField;
	public static Button pause_b;

	public static MyTimer timer;
	GameCanvas gameScr;

	public static void main(String[] args) {
		Tetris ts = new Tetris("Tetris Ver1.0\tAuthor: lxw0109");
		WindowListener wl = new WinListener();
		ts.addWindowListener(wl);
	}

	// 俄罗斯方块类的构造方法
	Tetris(String title) {
		super(title);

		this.setSize(600, 450);
		this.setResizable(false);
		this.setLayout(new GridLayout(1, 2));
		
		// #region		//1.Left region. Game display region.
		gameScr = new GameCanvas();
		// 键盘监听
		gameScr.addKeyListener(gameScr);

		// Thread
		timer = new MyTimer(gameScr);
		timer.setDaemon(true);
		timer.start();
		// timer.suspend(); 还必须得有，要不然出错
		timer.suspend();

		this.add(gameScr);
		// #endregion	//1

		// #region		//2.Right region. Game setting region.
		Panel rightScr = new Panel();
		rightScr.setLayout(new GridLayout(2, 1, 0, 0)); // rightScr.setLayout(new GridLayout(2, 1, 0, 30));
		rightScr.setSize(150, 450);
		this.add(rightScr);

		// #region		//2.1信息窗体
		// 右边信息窗体的布局
		MyPanel infoScr = new MyPanel();
		infoScr.setLayout(new GridLayout(4, 1, 0, 0));
		infoScr.setSize(150, 300);
		rightScr.add(infoScr);

		// 定义标签和初始值
		Label scorep = new Label("Score: ", Label.LEFT);
		Label levelp = new Label("Level: ", Label.LEFT);
		scoreField = new TextField(8);
		levelField = new TextField(8);
		scoreField.setEditable(false);
		levelField.setEditable(false);
		infoScr.add(scorep);
		infoScr.add(scoreField);
		infoScr.add(levelp);
		infoScr.add(levelField);
		scorep.setSize(new Dimension(10, 60));
		scoreField.setSize(new Dimension(10, 60));
		levelp.setSize(new Dimension(10, 60));
		levelField.setSize(new Dimension(10, 60));
		scoreField.setText("0");
		levelField.setText("1");
		// #endregion //2.1

		// #region		//2.2控制按钮
		// 右边控制按钮窗体的布局
		MyPanel controlScr = new MyPanel();
		controlScr.setLayout(new GridLayout(5, 1, 0, 5));
		rightScr.add(controlScr);

		// 定义按钮play
		Button play_b = new Button("Start");
		play_b.setSize(new Dimension(50, 200));
		play_b.addActionListener(new Command(Command.BUTTON_PLAY, gameScr));

		// 定义按钮Level UP
		Button level_up_b = new Button("Increase Level");
		level_up_b.setSize(new Dimension(50, 200));
		level_up_b.addActionListener(new Command(Command.BUTTON_LEVELUP, gameScr));

		// 定义按钮Level Down
		Button level_down_b = new Button("Decrease Level");
		level_down_b.setSize(new Dimension(50, 200));
		level_down_b.addActionListener(new Command(Command.BUTTON_LEVELDOWN, gameScr));

		// 定义按钮Level Pause
		pause_b = new Button("Pause");
		pause_b.setSize(new Dimension(50, 200));
		pause_b.addActionListener(new Command(Command.BUTTON_PAUSE, gameScr));

		// 定义按钮Quit
		Button quit_b = new Button("Quit");
		quit_b.setSize(new Dimension(50, 200));
		quit_b.addActionListener(new Command(Command.BUTTON_QUIT, gameScr));

		controlScr.add(play_b);
		controlScr.add(level_up_b);
		controlScr.add(level_down_b);
		controlScr.add(pause_b);
		controlScr.add(quit_b);
		// #endregion //2.2

		// #endregion	//2

		this.setVisible(true);
		gameScr.requestFocus();
	}
}

// 重写MyPanel类，使Panel的四周留空间
class MyPanel extends Panel {
	public Insets getInsets() {
		return new Insets(30, 50, 30, 50);
	}
}

// 游戏画布类
class GameCanvas extends Canvas implements KeyListener {
	final int unitSize = 30; // 小方块边长
	int rowNum; // 正方格的行数
	int columnNum; // 正方格的列数
	final int maxAllowRowNum; // 允许有多少行未削
	int blockInitRow; // 新出现块的起始行坐标
	int blockInitCol; // 新出现块的起始列坐标
	int[][] scrArr; // 屏幕数组
	Block b; // 方块的引用
	//boolean clearFlag;	// 是否清空

	// 画布类的构造方法
	GameCanvas() {
		this.rowNum = Tetris.ROW_NUM;
		this.columnNum = Tetris.COL_NUM;
		this.maxAllowRowNum = this.rowNum - 1;
		this.b = new Block(this);
		this.blockInitRow = rowNum - 1;
		this.blockInitCol = columnNum / 2 - 2;
		this.scrArr = new int[Tetris.ROW_NUM][Tetris.COL_NUM];
	}

	// 初始化屏幕，并将屏幕数组清零
	void initScr() {
		for (int i = 0; i < rowNum; i++)
			for (int j = 0; j < columnNum; j++)
				scrArr[i][j] = 0;
		b.reset();
		repaint();
	}

	// 清空屏幕
	void clearScr(){
		for (int i = 0; i < rowNum; i++)
			for (int j = 0; j < columnNum; j++)
				scrArr[i][j] = 0;
		repaint();
	}

	// 重新刷新画布
	public void paint(Graphics g) {
		for (int i = 0; i < rowNum; i++)
			for (int j = 0; j < columnNum; j++)
				drawUnit(i, j, scrArr[i][j]);
	}

	// 画方块
	public void drawUnit(int row, int col, int type) {
		//这句必须得有
		scrArr[row][col] = type;
		Graphics g = getGraphics();
		switch (type) { // 表示画方块
		case 0: {
			g.setColor(Color.yellow);
		}
			break; // 以背景为颜色画
		case 1: {
			g.setColor(Color.blue);
		}
			break; // 画正在下落的方块
		case 2: {
			g.setColor(Color.red);
		}
			break; // 画已经落下
		}
		//fill3DRect(int x, int y, int width, int height, boolean raised)
		g.fill3DRect(col * unitSize, this.getSize().height - (row + 1) * unitSize,
				unitSize, unitSize, true);
		//g.draw3DRect(col * unitSize, getSize().height - (row + 1) * unitSize,
		//		unitSize, unitSize, true);
		g.dispose();
		// 用完Graphics对象,不再使用的话,最好调用dispose()将其释放掉。
	}

	public Block getBlock() {
		return this.b; // 返回block实例的引用
	}

	// 返回屏幕数组中(row,col)位置的属性值
	public int getScrArrXY(int row, int col) {
		if (row < 0 || row >= rowNum || col < 0 || col >= columnNum)
			return -1;
		else
			return scrArr[row][col];
	}

	// 返回新块的初始行坐标
	public int getInitRow() {
		return this.blockInitRow; // 返回新块的初始行坐标
	}

	// 返回新块的初始列坐标
	public int getInitCol() {
		return this.blockInitCol; // 返回新块的初始列坐标
	}

	// 满行删除
	void deleteFullLine() {
		int full_line_num = 0;
		int k = 0;

		// 下面的这个for循环，不满行往下移动，覆盖满行
		for (int i = 0; i < rowNum; i++) {
			boolean isfull = true;
			for (int j = 0; j < columnNum; j++) {
				if (scrArr[i][j] == 0) {
					// k是用来标识当前处理的第几个"不满行"，刨除满行，包括空行
					k++;
					isfull = false;
					break;
				}
			}
			if (isfull) {
				full_line_num++;
			} else if(k - 1 != i){
				// if (k != 0 && k - 1 != i && !isfull){
				for (int j = 0; j < columnNum; j++) {
					drawUnit(k - 1, j, scrArr[i][j]);
					scrArr[k - 1][j] = scrArr[i][j];
				}
			}
		}

		Tetris.score += full_line_num;
		Tetris.scoreField.setText("" + Tetris.score);
	}

	// 判断游戏是否结束
	boolean isGameEnd() {
		for (int col = 0; col < columnNum; col++) {
			if (scrArr[this.maxAllowRowNum][col] != 0)
				return true;
		}
		return false;
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}

	// 处理键盘输入
	public void keyPressed(KeyEvent e) {
		if (!Tetris.isPlay || !Command.pause_resume)
			return;
		switch (e.getKeyCode()) {
		case KeyEvent.VK_DOWN:
			this.b.fallDown();
			break;
		case KeyEvent.VK_LEFT:
			this.b.leftMove();
			break;
		case KeyEvent.VK_RIGHT:
			this.b.rightMove();
			break;
		case KeyEvent.VK_SPACE:
			this.b.leftTurn();
			break;
		case KeyEvent.VK_UP:
			this.b.fastDown();
			break;
		}
	}
}

// 处理控制类
//鼠标点击事件处理
class Command implements ActionListener {
	static final int BUTTON_PLAY = 1; // 给按钮分配编号
	static final int BUTTON_LEVELUP = 2;
	static final int BUTTON_LEVELDOWN = 3;
	static final int BUTTON_QUIT = 4;
	static final int BUTTON_PAUSE = 5;
	static boolean pause_resume = true;

	int curButton; // 当前按钮
	GameCanvas scr;

	// 控制按钮类的构造方法
	Command(int button, GameCanvas scr) {
		this.curButton = button;
		this.scr = scr;
	}

	// 按钮执行
	public void actionPerformed(ActionEvent e) {
		switch (curButton) {
		case BUTTON_PLAY:
			if (!Tetris.isPlay) {
				scr.initScr();
				Tetris.isPlay = true;
				Tetris.score = 0;
				Tetris.scoreField.setText("0");
				Tetris.timer.resume();
			}
			scr.requestFocus();
			break;
		case BUTTON_LEVELUP:
			if (Tetris.level < 10) {
				Tetris.level++;
				Tetris.levelField.setText("" + Tetris.level);
				Tetris.score = 0;
				Tetris.scoreField.setText("" + Tetris.score);
			}
			scr.requestFocus();
			break;
		case BUTTON_LEVELDOWN:
			if (Tetris.level > 1) {
				Tetris.level--;
				Tetris.levelField.setText("" + Tetris.level);
				Tetris.score = 0;
				Tetris.scoreField.setText("" + Tetris.score);
			}
			scr.requestFocus();
			break;
		case BUTTON_PAUSE:
			if (pause_resume) {
				Tetris.timer.suspend();
				pause_resume = false;
				Tetris.pause_b.setLabel("Continue");
			} else {
				Tetris.timer.resume();
				pause_resume = true;
				Tetris.pause_b.setLabel("Pause");
			}
			scr.requestFocus();
			break;
		case BUTTON_QUIT:
			System.exit(0);
		}
	}
}

// 方块类
class Block {
	static int[][] pattern = {
			{ 0xf000, 0x4444, 0xf000, 0x4444 },	// 长条
			{ 0x4e00, 0x4640, 0x7200, 0x2620},	// 土
			{ 0x4620, 0x3600, 0x4620, 0x3600 },	// 左折断 
			{ 0x2640, 0xc600, 0x2640, 0xc600 },	// 右折断 
			{ 0x6440, 0xe200, 0x2260, 0x8e00 },	// 左拐尺
			{ 0x6220, 0x2e00, 0x4460, 0x7400 },	// 右拐尺
			{ 0x6600, 0x6600, 0x6600, 0x6600 } };	// 方块
	int blockType; // 块的模式号（0-6） 7个
	int turnState; // 块的翻转状态（0-3）	4个
	int blockState; // 块的下落状态
	int row, col; // 块在画布上的坐标
	GameCanvas scr;

	// 块类的构造方法
	Block(GameCanvas scr) {
		this.scr = scr;
		blockType = (int) (Math.random() * 1000) % 7;
		turnState = (int) (Math.random() * 1000) % 4;
		blockState = 1;
		row = scr.getInitRow();
		col = scr.getInitCol();
	}

	// 重新初始化块，并显示新块
	public void reset() {
		blockType = (int) (Math.random() * 1000) % 7;
		turnState = (int) (Math.random() * 1000) % 4;
		blockState = 1;
		row = scr.getInitRow();
		col = scr.getInitCol();
		dispBlock(1);
	}

	// 实现块翻转
	public void leftTurn() {
		if (assertValid(blockType, (turnState + 1) % 4, row, col)) {
			dispBlock(0);	// 擦掉上一个位置
			turnState = (turnState + 1) % 4;
			dispBlock(1);	// 在新的位置上画
		}
	}

	// 实现块的左移
	public void leftMove() {
		if (assertValid(blockType, turnState, row, col - 1)) {
			dispBlock(0);
			col--;
			dispBlock(1);
		}
	}

	// 实现块的右移
	public void rightMove() {
		if (assertValid(blockType, turnState, row, col + 1)) {
			dispBlock(0);
			col++;
			dispBlock(1);
		}
	}

	// 实现块落下的操作
	public boolean fallDown() {
		if (blockState == 2)
			return false;
		if (assertValid(blockType, turnState, row - 1, col)) {
			dispBlock(0);
			row--;
			dispBlock(1);
			return true;
		} else {
			blockState = 2;
			dispBlock(2);
			return false;
		}
	}

	// 实现块快速落下的操作
	public void fastDown() {
		int tempLevel = Tetris.level; 
		Tetris.level = 11;
		while (true) {
			if (assertValid(blockType, turnState, row - 1, col)) {
				dispBlock(0);
				row--;
				dispBlock(1);
			} else {
				blockState = 2;
				dispBlock(2);
				break;
			}
		}
		Tetris.level = tempLevel;
	}

	// 判断是否正确
	// 实参:			blockType, turnState
	boolean assertValid(int t, int s, int row, int col) {
		int k = 0x8000;	// 16 bits
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if ((pattern[t][s] & k) != 0) {
					int temp = scr.getScrArrXY(row - i, col + j);
					if (temp < 0 || temp == 2)
						return false;
				}
				k = k >> 1;
			}// inner for
		}// outer for.
		return true;
	}

	// 同步显示: 把当前块(blockType, turnState, row, col)显示出来
	// 参数0, 1, 2
	public synchronized void dispBlock(int s) {
		int k = 0x8000;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if ((pattern[blockType][turnState] & k) != 0) {
					scr.drawUnit(row - i, col + j, s);
				}
				k = k >> 1;
			}// inner for
		}// outer for
	}// dispBlock
}

// 定时线程
class MyTimer extends Thread {
	GameCanvas scr;

	public MyTimer(GameCanvas scr) {
		this.scr = scr;
	}

	public void run() {
		while (true) {
			try {
				sleep((10 - Tetris.level + 1) * 80);
			} catch (InterruptedException e) {
			}

			if (!scr.getBlock().fallDown()) {
				// 当有一个Block落地后，才进入此处执行
				scr.deleteFullLine();
				if (scr.isGameEnd()) {
					Tetris.isPlay = false;
					//MessageBox.show("GameOver.");
					JOptionPane.showMessageDialog(null, "Game Over!", "Information", JOptionPane.OK_CANCEL_OPTION);
					this.scr.clearScr();
					suspend();
				} else {
					scr.getBlock().reset();
				}
			}
		}
	}
}

class WinListener extends WindowAdapter {
	public void windowClosing(WindowEvent l) {
		System.exit(0);
	}
}