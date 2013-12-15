package yukkurisim;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Comparator;

import gamestatus.Const_Value;
import gamestatus.GameTimer;
import subwindow.Widget_Build;
import subwindow.Widget_Action;
import subwindow.Widget_Dialog;
import subwindow.Widget_Status;
import subwindow.Widget_ItemUse;

import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.background.ColorBackground;

public class Widget_Manager extends ADV_SpriteGroup_base {
	protected yukkurisim_main owner;					//オーナーGameインスタンスへの参照
	protected  Const_Value 定数 = new Const_Value();

	private Widget_Manager Windows;
    private Background 	background; 						// 背景
    private FontManager	FontMan = null;						// fontマネージャ
    
    private BufferedImage[]	DialogImage;
    private BufferedImage[]	MessageImage;
    private boolean BackupTimerStatus;
    ///// パート管理に使用する
    private Other_Object PhPointer;	// 物理法則クラスへのポインタのみ使用する
    private int LastPart = 定数.パート_施策_建築区分;
    
    public GameTimer GTimer;
    
    /******* メッセージウィジェットマネージャは複数存在し得ない *********/
	private static Widget_Manager	myself;
	private static Widget_Dialog	DialogWnd;
	private static Widget_Base		MessageWnd;

	private Widget_Base	Label_MesWnd;						// メッセージウインドウのラベル

	/******************* メッセージウインドウ関連 ***********************/
	private String	MessageStringnow;							// 現在表示せーって言われてる実際の文字列
	private StringBuffer MessageStringBuf;						// stringバッファ、実際に表示するのはこっち
	private int	MessageX;
	private int	MessageY;
	
	private int	MessageSpeedNow = 0;						// 現在のフレーム数、update毎にインクリメントされ
																// 定数.メッセージテキスト速度毎に１文字増える

	/********************* 施策パート・建築メニュー関連 *****************/
    private Widget_Build	BuildWindow;
    private Widget_Build	BuildWindow_B_01_tree;		// 木を立てるボタン
    private Widget_Build	BuildWindow_B_02_house;		// 家を建てるボタン
    private Widget_Build	BuildWindow_L_01_cost;		// 費用
    private Widget_Build	BuildWindow_L_02_cash;		// 所持金
    private Widget_Build	BuildWindow_B_99_exit;		// 閉じるボタン
	private Widget_Build	BuildWindow_B_100_remove;	// 撤去ボタン
	private Widget_Build	BuildWindow_B_03_farm;		// 農地ボタン
	
	/********************* 施策パート・行動メニュー関連 *****************/
	private Widget_Action	ActionWindow;
    private Widget_Action	ActionWindow_B_99_exit;		// 閉じるボタン
    private Widget_Action	ActionWindow_L_01_syuzoku;	// 種族名表示
    private Widget_Action	ActionWindow_L_02_nougyou;	// 種族名表示
    private Widget_Action	ActionWindow_L_03_kari;		// 種族名表示
    private Widget_Action	ActionWindow_O_01_image;	// 種族イメージ
    private Widget_Action	ActionWindow_L_04_karicheck;// チェックボックス部、狩り
    private Widget_Action	ActionWindow_L_05_nougyoucheck;// チェックボックス部、農業
    private Widget_Action	ActionWindow_L_06_asobicheck;// チェックボックス部、遊び
    // チェックボックス関連は Widget_Action.javaの中にrootwidget専用として直接書いた。

    /********************* 育成パート・メニューウインドウ関連 ***********************/
    private Widget_Base	menuwindow;			// メニューウインドウ本体枠

	private Widget_Base	CheckButton;
    private Widget_Base	ShopButton;
    private Widget_Base	ItemButton;
    private Widget_Base	TrapButton;
    private Widget_Base	BuildButton;
    private Widget_Base	CrashButton;
    private Widget_Base	DataButton;
    private Widget_Base	QuickButton;
    private Widget_Base	PanishButton;
    private Widget_Base	LoveButton;

    /********************* 育成パート・ステータスウインドウ関連 ***********************/
    private Widget_Status	StatusWindow;				// ステータスウインドウ
    private Widget_Status	StatusWindow_L_01_syuzoku;	// 種族ラベル
    private Widget_Status	StatusWindow_L_02_tairyoku;	// 体力ラベル
    private Widget_Status	StatusWindow_L_03_kokoro;	// こころラベル
    private Widget_Status	StatusWindow_L_04_chikara;	// ちからラベル
    private Widget_Status	StatusWindow_L_05_amasa;	// あまさラベル
    private Widget_Status	StatusWindow_L_06_onaka;	// おなかラベル
    private Widget_Status	StatusWindow_O_01_image;	// 種族イメージ
    private Widget_Status	StatusWindow_L_06_days;		// 日数ラベル
    private Widget_Status	StatusWindow_B_01_tojiru;	// 閉じるボタン
    
    /********************* 育成パート・ステータスウインドウ関連 ***********************/
    private Widget_ItemUse ItemUseWindow;				// アイテムウインドウ
    private Widget_ItemUse	ItemUseWindow_B_01;			// アイテムボタン１
    private Widget_ItemUse	ItemUseWindow_B_02;			// アイテムボタン２
    private Widget_ItemUse	ItemUseWindow_B_03;			// アイテムボタン３
    private Widget_ItemUse	ItemUseWindow_B_04;			// アイテムボタン４
    private Widget_ItemUse	ItemUseWindow_B_05;			// アイテムボタン５
    private Widget_ItemUse	ItemUseWindow_B_06;			// アイテムボタン６
    private Widget_ItemUse ItemUseWindow_B_07_left;	// 左ボタン
    private Widget_ItemUse ItemUseWindow_B_08_right;	// 右ボタン
    private Widget_ItemUse	ItemUseWindow_B_09_use;		// 使うボタン
    private Widget_ItemUse	ItemUseWindow_B_10_tojiru;	// 閉じるボタン
    private Widget_ItemUse	ItemUseWindow_L_01_kosuu;	// 個数ラベル

    /**
     * ターゲットウインドウのターゲッティングをまっているか
     * @return
     */
    public boolean isStatusWait()
    {
    	return this.StatusWindow.isTargetting();
    }
    
    /**
     * ターゲットウインドウのターゲッティング待ちを入力
     * @return
     */
    public void setStatusWait(boolean t)
    {
    	this.StatusWindow.setTargetting(t);
    }
    
	/**
	 * ステータスウインドウに値を表示します。
	 * @param syuzoku
	 * @param tairyoku
	 * @param kokoro
	 * @param chikara
	 * @param amasa
	 * @param onaka
	 */
	public void showStatusWidget(int syuzoku , int tairyoku , int kokoro , int chikara , int amasa , int onaka , int days)
	{
		StatusWindow.showStatusWidget(syuzoku, tairyoku, kokoro, chikara, amasa, onaka, days);
	}
	
	/********************************************************************/

	private Widget_Manager(yukkurisim_main own,String s)
	{
		super(own,s);
		owner = own;
	
		FontMan = new FontManager(owner,24);
		//DialogImage = owner.getImages("image/widget/dialogwindow.GIF", 1, 1);
		//MessageImage = owner.getImages("image/widget/MessageWindow.gif", 1, 1);
        //GTimer = new GameTimer(owner,owner.getImages("image/widget/timer.gif",1,1),0,0);
		DialogImage = ImageLoader.Get_Instance(owner).getBufferedImage(定数.画像番号_ダイアログウィンドウ);
		MessageImage = ImageLoader.Get_Instance(owner).getBufferedImage(定数.画像番号_メッセージウィンドウ);
        GTimer = GameTimer.Get_Instance(owner, 定数.画像番号_タイマ);
        PhPointer=new Other_Object(owner);


        MessageWnd = new Widget_Base(	own,
												MessageImage,
												0,
												定数.画面高さ - ( MessageImage[0].getHeight() ),
												null);
		MessageWnd.setActiveToFade(true);
		Windows = this;

		GTimer.Initialize();
		GTimer.Stop_Timer();					// 施策パートから始まるので、最初時間は止まっている
    	GTimer.setActive(false);				// ゲームタイマ
		
		Windows.add(MessageWnd);
		Windows.add(GTimer);

		
	    this.background = new ColorBackground(Color.black, 定数.画面幅, 定数.画面高さ);
	    Windows.setBackground(this.background);
	    
	    Label_MesWnd = new Widget_Base(owner,"",0,0,MessageWnd);
	    MessageWnd.Mount_widget(Label_MesWnd, 0, 0);
	    Label_MesWnd.setActiveToFade(true);
	    
		/******************* メッセージウインドウ関連 ***********************/
	    this.MessageStringnow="";
	    this.MessageX = (int)MessageWnd.getX();
	    this.MessageY = (int)MessageWnd.getY();
	    this.MessageStringBuf = new StringBuffer(256);		// 256文字まで
	    this.MessageStringnow = new String("");

		/********************* 育成メニューウインドウ関連 ***********************/

	    menuwindow = new Widget_Base(	owner,
    									//owner.getImages("image/widget/menu.gif",1,1),
    									ImageLoader.Get_Instance(owner).getBufferedImage(定数.画像番号_メニュ),
    									10,
    									100,
    									null
    									);
	    System.out.println("menuwindow->"+menuwindow.Get_Id());		//でばっぐ
    	// チェックボタンを追加
    	CheckButton = new Widget_Base(	owner,
    									//owner.getImages("image/widget/check_b.gif",1,1),
    									ImageLoader.Get_Instance(owner).getBufferedImage(定数.画像番号_チェックボタン),
    									0,0,
    									menuwindow);
	    System.out.println("CheckButton->"+CheckButton.Get_Id());		//でばっぐ


    	//	ショップボタンを追加
    	ShopButton = new Widget_Base(	owner,
										//owner.getImages("image/widget/shop_b.gif",1,1),
    									ImageLoader.Get_Instance(owner).getBufferedImage(定数.画像番号_ショップボタン),
										0,0,
										menuwindow);

    	//	アイテムボタンを追加
    	ItemButton = new Widget_Base(	owner,
										//owner.getImages("image/widget/item_b.gif",1,1),
    									ImageLoader.Get_Instance(owner).getBufferedImage(定数.画像番号_アイテムボタン),
										0,0,
										menuwindow);

    	//	トラップボタンを追加
    	TrapButton = new Widget_Base(	owner,
										//owner.getImages("image/widget/trap_b.gif",1,1),
    									ImageLoader.Get_Instance(owner).getBufferedImage(定数.画像番号_トラップボタン),
										0,0,
										menuwindow);

    	//	ビルドボタンを追加
    	BuildButton = new Widget_Base(	owner,
										//owner.getImages("image/widget/build_b.gif",1,1),
    									ImageLoader.Get_Instance(owner).getBufferedImage(定数.画像番号_ビルドボタン),
										0,0,
										menuwindow);
	    System.out.println("BuildButton->"+BuildButton.Get_Id());		//でばっぐ

    	//	クラッシュボタンを追加
    	CrashButton = new Widget_Base(	owner,
										//owner.getImages("image/widget/crash_b.gif",1,1),
    									ImageLoader.Get_Instance(owner).getBufferedImage(定数.画像番号_クラッシュボタン),
										0,0,
										menuwindow);

    	//	データボタンを追加
    	DataButton = new Widget_Base(	owner,
										//owner.getImages("image/widget/data_b.gif",1,1),
    									ImageLoader.Get_Instance(owner).getBufferedImage(定数.画像番号_データボタン),
										0,0,
										menuwindow);

    	//	クイックボタンを追加
    	QuickButton = new Widget_Base(	owner,
										//owner.getImages("image/widget/quick_b.gif",1,1),
    									ImageLoader.Get_Instance(owner).getBufferedImage(定数.画像番号_クイックボタン),
										0,0,
										menuwindow);

    	//	ぱにっすボタンを追加
    	PanishButton = new Widget_Base(	owner,
										//owner.getImages("image/widget/panish_b.gif",1,1),
    									ImageLoader.Get_Instance(owner).getBufferedImage(定数.画像番号_パニッシュボタン),
										0,0,
										menuwindow);

    	//	ラォァヴボタンを追加
    	LoveButton = new Widget_Base(	owner,
										//owner.getImages("image/widget/love_b.gif",1,1),
    									ImageLoader.Get_Instance(owner).getBufferedImage(定数.画像番号_ラブボタン),
										0,0,
										menuwindow);

    	/*menuwindow.Mount_widget(CheckButton, 3, 32);	//チェックボタンをマウント
    	menuwindow.Mount_widget(ShopButton, 73, 32);	//ショップボタンをマウント
    	menuwindow.Mount_widget(ItemButton,  3, 92);	//アイテムボタンをマウント
    	menuwindow.Mount_widget(TrapButton, 73, 92);	//トラップボタンをマウント
    	menuwindow.Mount_widget(BuildButton, 3,152);	//ビルドボタンをマウント
    	menuwindow.Mount_widget(CrashButton,73,152);	//クラッシュボタンをマウント
    	menuwindow.Mount_widget(DataButton,  3,212);	//データボタンをマウント
    	menuwindow.Mount_widget(QuickButton,73,212);	//クイックボタンをマウント
    	menuwindow.Mount_widget(PanishButton,3,272);	//ぱにっすボタンをマウント
    	menuwindow.Mount_widget(LoveButton, 73,272);	//ルァァアアアヴボタンをマウント
       	*/
    	
    	menuwindow.Mount_widget(CheckButton, 3, 32);	//チェックボタンをマウント
    	menuwindow.Mount_widget(ShopButton, 73, 32);	//ショップボタンをマウント
    	menuwindow.Mount_widget(ItemButton,  3, 92);	//アイテムボタンをマウント
    	menuwindow.Mount_widget(TrapButton, 73, 92);	//トラップボタンをマウント
    	menuwindow.Mount_widget(PanishButton,3,152);	//ぱにっすボタンをマウント
    	menuwindow.Mount_widget(LoveButton, 73,152);	//ルァァアアアヴボタンをマウント
    	menuwindow.Mount_widget(DataButton,  3,212);	//データボタンをマウント
    	menuwindow.Mount_widget(QuickButton,73,212);	//クイックボタンをマウント
       	
    	/*menuwindow.setActive(true);
    	CheckButton.setActive(true);
    	ShopButton.setActive(true);
    	ItemButton.setActive(true);
    	TrapButton.setActive(true);
    	BuildButton.setActive(true);
       	CrashButton.setActive(true);
       	DataButton.setActive(true);
       	QuickButton.setActive(true);
       	PanishButton.setActive(true);
       	LoveButton.setActive(true);
		// メニューウインドウは起動時非アクティブ
		*/
    	menuwindow.setActiveToFade(false);
       	Windows.add(menuwindow);
	    /********************************************************************/
	
	    /***************** ウインドウ系はスクロール不要 *********************/
	    MessageWnd.Set_Scrollable(false);
	    GTimer.Set_Scrollable(false);
	    menuwindow.Set_Scrollable(false);
	    
    	CheckButton.Set_Scrollable(false);
    	ShopButton.Set_Scrollable(false);
    	ItemButton.Set_Scrollable(false);
    	TrapButton.Set_Scrollable(false);
    	BuildButton.Set_Scrollable(false);
       	CrashButton.Set_Scrollable(false);
       	DataButton.Set_Scrollable(false);
       	QuickButton.Set_Scrollable(false);
       	PanishButton.Set_Scrollable(false);
       	LoveButton.Set_Scrollable(false);
	    /********************************************************************/

       	/************************ buildウインドウinitialize******************/
       	this.BuildWindow = new Widget_Build(	owner,
       											//owner.getImages("image/widget/window_build.GIF",1,1),
      											ImageLoader.Get_Instance(owner).getBufferedImage(定数.画像番号_ビルドウインドウ),
      											15,15,
       											null);
       	this.BuildWindow_B_01_tree = new Widget_Build(	owner,
       													//owner.getImages("image/widget/window_build_B_01.GIF",1,1),
       													ImageLoader.Get_Instance(owner).getBufferedImage(定数.画像番号_木ボタン),
       													0,0,
       													BuildWindow,
       													定数.ボタンID_木ボタン);
       	this.BuildWindow_B_02_house = new Widget_Build(	owner,
															//owner.getImages("image/widget/window_build_B_01.GIF",1,1),
															ImageLoader.Get_Instance(owner).getBufferedImage(定数.画像番号_家ボタン),
															0,0,
															BuildWindow,
															定数.ボタンID_家ボタン);
       	this.BuildWindow_B_03_farm = new Widget_Build(	owner,
       														ImageLoader.Get_Instance(owner).getBufferedImage(定数.画像番号_農地ボタン),
       														0,0,
       														BuildWindow,
       														定数.ボタンID_農地ボタン);

       	this.BuildWindow_B_99_exit = new Widget_Build(	owner,
       													//owner.getImages("image/widget/owaru.GIF",1,1),
       													ImageLoader.Get_Instance(owner).getBufferedImage(定数.画像番号_閉じるボタン),
       													0,0,
       													BuildWindow,
       													定数.ボタンID_閉じるボタン);
       	this.BuildWindow_B_100_remove = new Widget_Build(	owner,
       														//owner.getImages("image/widget/tekkyo.GIF",1,1),
       														ImageLoader.Get_Instance(owner).getBufferedImage(定数.画像番号_撤去ボタン),
       														0,0,
       														BuildWindow,
       														定数.ボタンID_撤去ボタン);
       	this.BuildWindow_L_01_cost = new Widget_Build(	owner,
       													"費用　：",
       													0,0,
       													BuildWindow,
       													定数.ラベルID_建築_費用);
       	BuildWindow_L_01_cost.SetFontsize(15);		// フォントサイズを変更
       	this.BuildWindow_L_02_cash = new Widget_Build(	owner,
       													"所持金：",
       													0,0,
       													BuildWindow,
       													定数.ラベルID_建築_所持金);
       	this.BuildWindow_L_02_cash.SetFontsize(15);

       	this.BuildWindow.Mount_widget(BuildWindow_B_01_tree, 10, 20);
       	this.BuildWindow.Mount_widget(BuildWindow_B_02_house, 10, 70);
       	this.BuildWindow.Mount_widget(BuildWindow_L_01_cost,125,20);
       	this.BuildWindow.Mount_widget(BuildWindow_L_02_cash, 125, 35);
       	this.BuildWindow.Mount_widget(BuildWindow_B_99_exit, 180, 210);
       	this.BuildWindow.Mount_widget(BuildWindow_B_100_remove, 180, 170);
       	this.BuildWindow.Mount_widget(BuildWindow_B_03_farm,10,120);

       	this.BuildWindow.setActiveToFade(true);
       	this.BuildWindow.Set_Scrollable(false);
       	//this.BuildWindow_B_01_tree.setActive(false);
       	this.BuildWindow_B_01_tree.Set_Scrollable(false);
       	
       	Windows.add(BuildWindow);
       	Windows.add(BuildWindow_B_01_tree);
       	Windows.add(BuildWindow_B_02_house);
       	Windows.add(BuildWindow_B_99_exit);
       	Windows.add(BuildWindow_B_100_remove);
       	Windows.add(BuildWindow_L_01_cost);
       	Windows.add(BuildWindow_L_02_cash);
       	Windows.add(BuildWindow_B_03_farm);
       	
       	/************************ actionウインドウinitialize******************/
        // チェックボックス関連は Widget_Action.javaの中にrootwidget専用として直接書いた。
       	this.ActionWindow = new Widget_Action(	owner,
												//owner.getImages("image/widget/window_action.GIF",1,1),
												ImageLoader.Get_Instance(owner).getBufferedImage(定数.画像番号_アクションウインドウ),
												0,0,
												null);
       	this.ActionWindow_B_99_exit = new Widget_Action(	owner,
															//owner.getImages("image/widget/owaru.GIF",1,1),
															ImageLoader.Get_Instance(owner).getBufferedImage(定数.画像番号_閉じるボタン),
															0,0,
															ActionWindow,
															定数.ボタンID_閉じるボタン);
       	this.ActionWindow_L_01_syuzoku = new Widget_Action(	owner,
       															"",
       															0,0,
       															ActionWindow,
       															定数.ラベルID_アクション_種族名);
       	ActionWindow_L_01_syuzoku.SetFontsize(15);		// フォントサイズ調整
       	this.ActionWindow_L_02_nougyou = new Widget_Action(	owner,
       															"",
       															0,0,
       															ActionWindow,
       															定数.ラベルID_アクション_農業);
       	ActionWindow_L_02_nougyou.SetFontsize(15);		// フォントサイズ調整
       	this.ActionWindow_L_03_kari = new Widget_Action(		owner,
       															"",
       															0,0,
       															ActionWindow,
       															定数.ラベルID_アクション_狩り);
       	ActionWindow_L_03_kari.SetFontsize(15);		// フォントサイズ調整
       	this.ActionWindow_L_04_karicheck = new Widget_Action(	owner,
       															"狩り",
       															0,0,
       															ActionWindow,
       															定数.ラベルID_アクション_説明_狩り);
       	this.ActionWindow_L_05_nougyoucheck = new Widget_Action(	owner,
       																"農業",
       																0,0,
       																ActionWindow,
       																定数.ラベルID_アクション_説明_農業);
       	this.ActionWindow_L_06_asobicheck = new Widget_Action(	owner,
       															"遊ぶ",
       															0,0,
       															ActionWindow,
       															定数.ラベルID_アクション_説明_遊び);
       	this.ActionWindow_O_01_image = new Widget_Action( 	owner,
       														(BufferedImage[])null,
       														0,0,
       														ActionWindow,
       														定数.その他ID_画像表示);
       	
       	this.ActionWindow.Mount_widget(ActionWindow_B_99_exit, 200, 200);
       	this.ActionWindow.Mount_widget(ActionWindow_L_01_syuzoku, 70, 30);
       	this.ActionWindow.Mount_widget(ActionWindow_L_02_nougyou, 70, 54);
       	this.ActionWindow.Mount_widget(ActionWindow_L_03_kari, 70, 78);
       	this.ActionWindow.Mount_widget(ActionWindow_O_01_image, 10, 30);
       	this.ActionWindow.Mount_widget(ActionWindow_L_04_karicheck, 40, 115);
       	this.ActionWindow.Mount_widget(ActionWindow_L_05_nougyoucheck, 120, 115);
       	this.ActionWindow.Mount_widget(ActionWindow_L_06_asobicheck, 200, 115);
       	
       	this.ActionWindow.setActive(false);
       	this.ActionWindow.Set_Scrollable(false);
       	Windows.add(ActionWindow);
       	Windows.add(ActionWindow_B_99_exit);
       	Windows.add(ActionWindow_L_01_syuzoku);
       	Windows.add(ActionWindow_L_02_nougyou);
       	Windows.add(ActionWindow_L_03_kari);
       	Windows.add(ActionWindow_O_01_image);
       	Windows.add(ActionWindow_L_04_karicheck);
       	Windows.add(ActionWindow_L_05_nougyoucheck);
       	Windows.add(ActionWindow_L_06_asobicheck);
       	
       	/************************ statusウインドウinitialize******************/
       	this.StatusWindow = new Widget_Status(	owner,
       											//owner.getImages("image/widget/window_status.GIF",1,1),
       											ImageLoader.Get_Instance(owner).getBufferedImage(定数.画像番号_ステータスウインドウ),
       											0,0,
       											null);
       	this.StatusWindow_L_01_syuzoku = new Widget_Status(	owner,
       															"種族",
       															0,0,
       															StatusWindow,
       															定数.ラベルID_ステータス_種族);
       	this.StatusWindow_L_01_syuzoku.SetFontsize(15);
       	this.StatusWindow_L_02_tairyoku =  new Widget_Status(	owner,
       															"こころ",
       															0,0,
       															StatusWindow,
       															定数.ラベルID_ステータス_体力);

       	this.StatusWindow_L_02_tairyoku.SetFontsize(15);

       	this.StatusWindow_L_03_kokoro =  new Widget_Status(	owner,
       															"こころ",
       															0,0,
       															StatusWindow,
       															定数.ラベルID_ステータス_こころ);
       	this.StatusWindow_L_03_kokoro.SetFontsize(15);

       	this.StatusWindow_L_04_chikara = new Widget_Status(	owner,
       														"ちから",
       														0,0,
       														StatusWindow,
       														定数.ラベルID_ステータス_ちから);
       	this.StatusWindow_L_04_chikara.SetFontsize(15);

       	this.StatusWindow_L_05_amasa =  new Widget_Status(	owner,
       														"甘さ",
       														0,0,
       														StatusWindow,
       														定数.ラベルID_ステータス_甘さ);
       	this.StatusWindow_L_05_amasa.SetFontsize(15);

       	this.StatusWindow_L_06_onaka =  new Widget_Status(	owner,
       														"おなか",
       														0,0,
       														StatusWindow,
       														定数.ラベルID_ステータス_おなか);
       	this.StatusWindow_L_06_onaka.SetFontsize(15);

       	this.StatusWindow_B_01_tojiru = new Widget_Status(	owner,
       														//owner.getImages("image/widget/owaru.GIF",1,1),
       														ImageLoader.Get_Instance(owner).getBufferedImage(定数.画像番号_閉じるボタン),
       														0,0,
       														StatusWindow,
       														定数.ボタンID_閉じるボタン);
       	this.StatusWindow_O_01_image = new Widget_Status( 	owner,
       														(BufferedImage[])null,
       														0,0,
       														StatusWindow,
       														定数.その他ID_画像表示);
       	this.StatusWindow_L_06_days = new Widget_Status(	owner,
       														"日数",
       														0,0,
       														StatusWindow,
       														定数.ラベルID_ステータス_日数);
       	this.StatusWindow_L_06_days.SetFontsize(15);

       	this.StatusWindow.Mount_widget(StatusWindow_L_01_syuzoku, 96, 32);
       	this.StatusWindow.Mount_widget(StatusWindow_L_02_tairyoku, 96, 64);
       	this.StatusWindow.Mount_widget(StatusWindow_L_03_kokoro, 96, 96);
       	this.StatusWindow.Mount_widget(StatusWindow_L_04_chikara, 96, 128);
       	this.StatusWindow.Mount_widget(StatusWindow_L_05_amasa, 96, 160);
       	this.StatusWindow.Mount_widget(StatusWindow_L_06_onaka, 96, 192);
       	this.StatusWindow.Mount_widget(StatusWindow_B_01_tojiru,208,176);
       	this.StatusWindow.Mount_widget(StatusWindow_O_01_image,208,40);
       	this.StatusWindow.Mount_widget(StatusWindow_L_06_days,208,128);
       	this.StatusWindow.setActive(false);
       	this.StatusWindow.Set_Scrollable(false);
       	
       	Windows.add(StatusWindow);
       	Windows.add(StatusWindow_L_01_syuzoku);
       	Windows.add(StatusWindow_L_02_tairyoku);
       	Windows.add(StatusWindow_L_03_kokoro);
       	Windows.add(StatusWindow_L_04_chikara);
       	Windows.add(StatusWindow_L_05_amasa);
       	Windows.add(StatusWindow_L_06_onaka);
       	Windows.add(StatusWindow_B_01_tojiru);
       	Windows.add(StatusWindow_O_01_image);
       	Windows.add(StatusWindow_L_06_days);

       	/************************ ItemUseウインドウinitialize******************/
       	this.ItemUseWindow = new Widget_ItemUse(	owner,
       												//owner.getImages("image/widget/window_ItemUse.GIF",1,1),
       												ImageLoader.Get_Instance(owner).getBufferedImage(定数.画像番号_アイテムウインドウ),
       												定数.画面幅-140,10,
       												null);
       	this.ItemUseWindow_B_01 = new Widget_ItemUse(	owner,
       													(BufferedImage[])null,
       													0,0,
       													ItemUseWindow,
       													定数.ボタンID_アイテム01);
       	this.ItemUseWindow_B_10_tojiru = new Widget_ItemUse(	owner,
       															//owner.getImages("image/widget/owaru.GIF",1,1),
       															ImageLoader.Get_Instance(owner).getBufferedImage(定数.画像番号_閉じるボタン),
       															0,0,
       															ItemUseWindow,
       															定数.ボタンID_閉じるボタン);
       	this.ItemUseWindow_B_01 = new Widget_ItemUse(	owner,
       													(BufferedImage[])null,
       													0,0,
       													ItemUseWindow,
       													定数.ボタンID_アイテム01);
       	this.ItemUseWindow_B_02 = new Widget_ItemUse(	owner,
					(BufferedImage[])null,
					0,0,
					ItemUseWindow,
					定数.ボタンID_アイテム02);
       	this.ItemUseWindow_B_03 = new Widget_ItemUse(	owner,
					(BufferedImage[])null,
					0,0,
					ItemUseWindow,
					定数.ボタンID_アイテム03);
       	this.ItemUseWindow_B_04 = new Widget_ItemUse(	owner,
					(BufferedImage[])null,
					0,0,
					ItemUseWindow,
					定数.ボタンID_アイテム04);
       	this.ItemUseWindow_B_05 = new Widget_ItemUse(	owner,
					(BufferedImage[])null,
					0,0,
					ItemUseWindow,
					定数.ボタンID_アイテム05);
       	this.ItemUseWindow_B_06 = new Widget_ItemUse(	owner,
					(BufferedImage[])null,
					0,0,
					ItemUseWindow,
					定数.ボタンID_アイテム06);

       	ItemUseWindow.Mount_widget(ItemUseWindow_B_10_tojiru, 35, 245);
       	ItemUseWindow.Mount_widget(ItemUseWindow_B_01, 15, 30);
       	ItemUseWindow.Mount_widget(ItemUseWindow_B_02, 70, 30);
       	ItemUseWindow.Mount_widget(ItemUseWindow_B_03, 15, 85);
       	ItemUseWindow.Mount_widget(ItemUseWindow_B_04, 70, 85);
       	ItemUseWindow.Mount_widget(ItemUseWindow_B_05, 15, 140);
       	ItemUseWindow.Mount_widget(ItemUseWindow_B_06, 70, 140);
       	ItemUseWindow.setActive(false);
       	ItemUseWindow.Set_Scrollable(false);

       	Windows.add(ItemUseWindow);
       	Windows.add(ItemUseWindow_B_10_tojiru);
       	Windows.add(ItemUseWindow_B_01);
       	Windows.add(ItemUseWindow_B_02);
       	Windows.add(ItemUseWindow_B_03);
       	Windows.add(ItemUseWindow_B_04);
       	Windows.add(ItemUseWindow_B_05);
       	Windows.add(ItemUseWindow_B_06);
 
       	/******************* ダイアログウインドウ関連 ***********************/
       	//ダイアログウインドウは表示順序の関係から、一番最後にインスタンス化する
        DialogWnd = new Widget_Dialog(	own ,
												DialogImage,
												( 定数.画面幅 / 2 ) - ( DialogImage[0].getWidth() / 2 ),
												定数.画面高さ / 2 - ( DialogImage[0].getHeight() / 2 ),
												null,
												定数.ボタンID_ヌル);
		
		DialogWnd.setActive(false);
		DialogWnd.Set_Scrollable(false);
		Windows.add(DialogWnd);

       	/************* Comparator セット **************/
    	this.setComparator(
    			new Comparator(){
    				public int compare(Object o1, Object o2){
    					Object_base	s1 = (Object_base) o1,
    								s2 = (Object_base) o2;
    					return (int)(s1.Get_Id() - s2.Get_Id() );
    				}
    			}
    	);
    	
    	/************* このグループは非アクティブなオブジェクトを削除しない **********/
    	this.getScanFrequence().setActive(false);

	}
	
	/******* インスタンスを得る(親インスタンスと引き換え) ********/
	public static synchronized Widget_Manager Get_Instance( yukkurisim_main own )
	{
		if( myself == null)
		{
			myself = new Widget_Manager(own , "WIDGET_MANAGER");
		}

		return myself;
	}
	
	//　表示するスプライトグループを取得する。
	public Widget_Manager GetWindowGroup()
	{
		return this.Windows;
	}
	
	public void Popup_Dialog_Window(String message)
	{
		DialogWnd.Popup_Dialog_Window(message);
	}
	public void Kill_Dialog_Window()
	{
		DialogWnd.Kill_Dialog_Window();
	}
	
	public boolean DialogisClicked()
	{
		return DialogWnd.isClicked();
	}

	public boolean MessageisClicked()
	{
		return MessageWnd.isClicked();
	}

    @Override
	public void update(long elapsedTime)
	{	
    	// ダイアログ処理以外はタイマに従属する
    	if( GTimer.isMoving())	
    	{
	    	GTimer.Time_Inc();					//Timerを進める
    	}
    	// パート変更時の表示対象ウインドウ変更処理
    	if( PhPointer.PhLaw.Get_Part()!=LastPart )
    	{
    		LastPart = PhPointer.PhLaw.Get_Part();
    		if(LastPart==定数.パート_施策_建築区分)
    		{
    			// 施策建築パートの時は建築ウインドウのみアクティブ
    			this.closeAllWindow();	// 一度全てのウインドウを閉じる
    			
    			this.BuildWindow.setActiveToFade(true);
    			this.ActionWindow.setActiveToFade(false);
    			this.menuwindow.setActiveToFade(false);
    			this.GTimer.setActiveToFade(false);
    			this.GTimer.Stop_Timer();
    			this.GTimer.Reset_Timer();	// ■注意！この行は実装され次第襲撃パートのブロックに入るべき
    		}
    		else if(LastPart==定数.パート_施策_行動区分)
    		{
    			this.closeAllWindow();	// 一度全てのウインドウを閉じる

    			this.BuildWindow.setActiveToFade(false);
    			this.ActionWindow.Reset_Cooperate_AVG_List();
    			this.ActionWindow.Reset_Hunting_AVG_List();
    			this.ActionWindow.setActiveToFade(true);
    			this.menuwindow.setActiveToFade(false);
    			this.GTimer.setActiveToFade(false);
    			this.GTimer.Stop_Timer();
    		}
    		else if(LastPart==定数.パート_育成区分)
    		{
    			// 育成パートの時は育成メニュー及びタイマがアクティブ
    			this.closeAllWindow();	// 一度全てのウインドウを閉じる

    			this.BuildWindow.setActiveToFade(false);
    			this.ActionWindow.setActiveToFade(false);
    			this.menuwindow.setActiveToFade(true);
    			this.GTimer.setActiveToFade(true);
    			this.GTimer.Start_Timer();
    			
    		}
    		else if(LastPart==定数.パート_襲撃区分)
    		{
    			
    		}
    	}
    	this.MessageSpeedNow++;
	    	
		/* ここまで */
		/*if ( testwindow.isClicked())
		{
		}*/
		/*else if ( owner.click()) 
        {
            testwindow.setActive(!testwindow.isActive());
        }*/

		// チェックボタンが押されたら
		if ( CheckButton.isClicked() )
		{
			if(Widget_Manager.Get_Instance(owner).getMessenger()!=this)
			{
				Widget_Manager.Get_Instance(owner).ClsMessage();
				Widget_Manager.Get_Instance(owner).DrawMessage("どいつの状態を見るの？", this);
			}
			this.BackupTimerStatus=GTimer.isMoving();
			GTimer.Stop_Timer();
			this.StatusWindow.setTargetting(true);
		}
		
		// ショップボタンが押されたら
		if ( ShopButton.isClicked() )
		{
			Widget_Manager.Get_Instance(owner).Popup_Dialog_Window("ショップは未実装です。");		// 17文字
			System.out.println("ショップボタン");
		}
		
		//　アイテムボタンが押されたら
		if ( ItemButton.isClicked() )
		{
			this.ItemUseWindow.setActiveToFade(true);
		}
		//　トラップボタンが押されたら
		if ( TrapButton.isClicked() )
		{
			GameSaveLoader gamesaver = new GameSaveLoader();
			gamesaver.SaveState(owner);
			System.out.println("ゲームをセーブしました。　終了します。");
			System.exit(0);
		}    	
    	// クリック検出はManagerで行い、具体的な処理は新しくクラスを作成してそちら側に書く
		//　ビルドボタンが押されたら
		if ( BuildButton.isClicked() )
		{
			this.BuildWindow.setActiveToFade(true);				// ビルドウインドウ自体のアクティブ化
		}
		//BuildWindow.update(elapsedTime);					// ビルドウインドウのアップデート
		//ActionWindow.update(elapsedTime);
		super.update(elapsedTime);
    	this.doClickEvent();
    	
		
		//　クラッシュマンが倒せない
		if ( CrashButton.isClicked() )
		{
			Widget_Manager.Get_Instance(owner).Popup_Dialog_Window("crashは未実装です。");		// 17文字
		}

		//　データボタンが押されたら
		if ( DataButton.isClicked() )
		{
			Widget_Manager.Get_Instance(owner).Popup_Dialog_Window("dataは未実装です。");		// 17文字
		}

		//　クイックボタンが押されたら
		if ( QuickButton.isClicked() )
		{
			Widget_Manager.Get_Instance(owner).Popup_Dialog_Window("quickは未実装です。");		// 17文字
		}

		//　ぱにっすボタンが押されたら
		if ( PanishButton.isClicked() )
		{
			Widget_Manager.Get_Instance(owner).Popup_Dialog_Window("panishは未実装です。");		// 17文字
		}
		//　ッウ・・・・ルゥァーーーゥヴボタン が押されたら
		if ( LoveButton.isClicked() )
		{
			Widget_Manager.Get_Instance(owner).Popup_Dialog_Window("Loveは未実装です。");		// 17文字
		}
  
    	// ダイアログがクリックされたら切る
    	// ダイアログが出ている間は原則タイマーを停止する

    	/**************** サブウインドウ系 ***********************/
       	//this.BuildWindow.update(elapsedTime);
       	//this.BuildWindow_B_01_tree.update(elapsedTime);

    	
	}
    
    /**
     * メッセージウインドウに文字を表示する.
     * calledobjに参照を登録しておくと現在表示中のメッセージが誰のものかわかり
     * if文の比較が比較的高速に行える（かな？）
     * @param message
     * @param calledobj
     */
    private Object nowMessenger = null;

    /**
     * 
     * @return
     */
    public Object getMessenger()
    {
    	return nowMessenger;
    }
    
    public void DrawMessage(String message, Object calledobj)
    {
    	System.out.println("DrawMessage in");
    	this.MessageStringnow = message;
    	nowMessenger = calledobj;
    }
    
    public void ClsMessage()
    {
    	System.out.println("ClsMessage in");
    	
    	this.MessageStringnow = "";
    	this.MessageSpeedNow = 0;
    	this.MessageStringBuf.delete(0, this.MessageStringBuf.length());
    	nowMessenger = null;
    }
    
    public void incdam()
    {
    	//デバッグ用ダミーファンクション
    	
    }
    
    @Override
	public void render(Graphics2D g)
    {
    	super.render(g);
    	//メッセージウインドウ データ表示部
    	if(this.MessageStringBuf.length() != this.MessageStringnow.length())
    	{
    		if(this.MessageSpeedNow >= 定数.メッセージテキスト速度)
    		{
    			this.MessageStringBuf.append(this.MessageStringnow.charAt(this.MessageStringBuf.length()));
    			this.MessageSpeedNow=0;
    		}
    	}
    	//System.out.println("メッセージデバッグ→"+this.MessageStringnow);
    	FontMan.drawString(g, this.MessageStringBuf.toString(), this.MessageX, this.MessageY);
    }
    
    /**
     * 全てのウインドウを閉じます
     *
     */
    public void closeAllWindow()
    {
    	BuildWindow.setActiveToFade(false);
    	ActionWindow.setActiveToFade(false);
    	menuwindow.setActiveToFade(false);
    	StatusWindow.setActiveToFade(false);
    	ItemUseWindow.setActiveToFade(false);

    }
	
}

/*
 * 時空戦士 ゴンガレ テーマソング
「 時空戦士ゴンガレのテーマ 」
　　作詞　海越 次郎
　　作曲　オッタマゲビッチ・ヤタラツモルスキー

♪ゴンゴンゴン！　ゴンゴンゴン！
　見よ！ 海を見よ！ 太陽が君の背中を！
　押している！ 渦巻いているぞ！ クロスゴンガレオン・ビッチ！
　あの竜巻が　希望だったなら　空はさぞかし　明るいだろう
　あの星雲が　勇気だったなら　君の涙は　イデオロギー
　はてしなく！　はてしなく！　ゴンガレ！　ゴンガレ！
 * 
 */