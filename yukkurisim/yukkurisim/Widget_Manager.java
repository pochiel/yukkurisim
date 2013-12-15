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
	protected yukkurisim_main owner;					//�I�[�i�[Game�C���X�^���X�ւ̎Q��
	protected  Const_Value �萔 = new Const_Value();

	private Widget_Manager Windows;
    private Background 	background; 						// �w�i
    private FontManager	FontMan = null;						// font�}�l�[�W��
    
    private BufferedImage[]	DialogImage;
    private BufferedImage[]	MessageImage;
    private boolean BackupTimerStatus;
    ///// �p�[�g�Ǘ��Ɏg�p����
    private Other_Object PhPointer;	// �����@���N���X�ւ̃|�C���^�̂ݎg�p����
    private int LastPart = �萔.�p�[�g_�{��_���z�敪;
    
    public GameTimer GTimer;
    
    /******* ���b�Z�[�W�E�B�W�F�b�g�}�l�[�W���͕������݂����Ȃ� *********/
	private static Widget_Manager	myself;
	private static Widget_Dialog	DialogWnd;
	private static Widget_Base		MessageWnd;

	private Widget_Base	Label_MesWnd;						// ���b�Z�[�W�E�C���h�E�̃��x��

	/******************* ���b�Z�[�W�E�C���h�E�֘A ***********************/
	private String	MessageStringnow;							// ���ݕ\�����[���Č����Ă���ۂ̕�����
	private StringBuffer MessageStringBuf;						// string�o�b�t�@�A���ۂɕ\������̂͂�����
	private int	MessageX;
	private int	MessageY;
	
	private int	MessageSpeedNow = 0;						// ���݂̃t���[�����Aupdate���ɃC���N�������g����
																// �萔.���b�Z�[�W�e�L�X�g���x���ɂP����������

	/********************* �{��p�[�g�E���z���j���[�֘A *****************/
    private Widget_Build	BuildWindow;
    private Widget_Build	BuildWindow_B_01_tree;		// �؂𗧂Ă�{�^��
    private Widget_Build	BuildWindow_B_02_house;		// �Ƃ����Ă�{�^��
    private Widget_Build	BuildWindow_L_01_cost;		// ��p
    private Widget_Build	BuildWindow_L_02_cash;		// ������
    private Widget_Build	BuildWindow_B_99_exit;		// ����{�^��
	private Widget_Build	BuildWindow_B_100_remove;	// �P���{�^��
	private Widget_Build	BuildWindow_B_03_farm;		// �_�n�{�^��
	
	/********************* �{��p�[�g�E�s�����j���[�֘A *****************/
	private Widget_Action	ActionWindow;
    private Widget_Action	ActionWindow_B_99_exit;		// ����{�^��
    private Widget_Action	ActionWindow_L_01_syuzoku;	// �푰���\��
    private Widget_Action	ActionWindow_L_02_nougyou;	// �푰���\��
    private Widget_Action	ActionWindow_L_03_kari;		// �푰���\��
    private Widget_Action	ActionWindow_O_01_image;	// �푰�C���[�W
    private Widget_Action	ActionWindow_L_04_karicheck;// �`�F�b�N�{�b�N�X���A���
    private Widget_Action	ActionWindow_L_05_nougyoucheck;// �`�F�b�N�{�b�N�X���A�_��
    private Widget_Action	ActionWindow_L_06_asobicheck;// �`�F�b�N�{�b�N�X���A�V��
    // �`�F�b�N�{�b�N�X�֘A�� Widget_Action.java�̒���rootwidget��p�Ƃ��Ē��ڏ������B

    /********************* �琬�p�[�g�E���j���[�E�C���h�E�֘A ***********************/
    private Widget_Base	menuwindow;			// ���j���[�E�C���h�E�{�̘g

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

    /********************* �琬�p�[�g�E�X�e�[�^�X�E�C���h�E�֘A ***********************/
    private Widget_Status	StatusWindow;				// �X�e�[�^�X�E�C���h�E
    private Widget_Status	StatusWindow_L_01_syuzoku;	// �푰���x��
    private Widget_Status	StatusWindow_L_02_tairyoku;	// �̗̓��x��
    private Widget_Status	StatusWindow_L_03_kokoro;	// �����냉�x��
    private Widget_Status	StatusWindow_L_04_chikara;	// �����烉�x��
    private Widget_Status	StatusWindow_L_05_amasa;	// ���܂����x��
    private Widget_Status	StatusWindow_L_06_onaka;	// ���Ȃ����x��
    private Widget_Status	StatusWindow_O_01_image;	// �푰�C���[�W
    private Widget_Status	StatusWindow_L_06_days;		// �������x��
    private Widget_Status	StatusWindow_B_01_tojiru;	// ����{�^��
    
    /********************* �琬�p�[�g�E�X�e�[�^�X�E�C���h�E�֘A ***********************/
    private Widget_ItemUse ItemUseWindow;				// �A�C�e���E�C���h�E
    private Widget_ItemUse	ItemUseWindow_B_01;			// �A�C�e���{�^���P
    private Widget_ItemUse	ItemUseWindow_B_02;			// �A�C�e���{�^���Q
    private Widget_ItemUse	ItemUseWindow_B_03;			// �A�C�e���{�^���R
    private Widget_ItemUse	ItemUseWindow_B_04;			// �A�C�e���{�^���S
    private Widget_ItemUse	ItemUseWindow_B_05;			// �A�C�e���{�^���T
    private Widget_ItemUse	ItemUseWindow_B_06;			// �A�C�e���{�^���U
    private Widget_ItemUse ItemUseWindow_B_07_left;	// ���{�^��
    private Widget_ItemUse ItemUseWindow_B_08_right;	// �E�{�^��
    private Widget_ItemUse	ItemUseWindow_B_09_use;		// �g���{�^��
    private Widget_ItemUse	ItemUseWindow_B_10_tojiru;	// ����{�^��
    private Widget_ItemUse	ItemUseWindow_L_01_kosuu;	// �����x��

    /**
     * �^�[�Q�b�g�E�C���h�E�̃^�[�Q�b�e�B���O���܂��Ă��邩
     * @return
     */
    public boolean isStatusWait()
    {
    	return this.StatusWindow.isTargetting();
    }
    
    /**
     * �^�[�Q�b�g�E�C���h�E�̃^�[�Q�b�e�B���O�҂������
     * @return
     */
    public void setStatusWait(boolean t)
    {
    	this.StatusWindow.setTargetting(t);
    }
    
	/**
	 * �X�e�[�^�X�E�C���h�E�ɒl��\�����܂��B
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
		DialogImage = ImageLoader.Get_Instance(owner).getBufferedImage(�萔.�摜�ԍ�_�_�C�A���O�E�B���h�E);
		MessageImage = ImageLoader.Get_Instance(owner).getBufferedImage(�萔.�摜�ԍ�_���b�Z�[�W�E�B���h�E);
        GTimer = GameTimer.Get_Instance(owner, �萔.�摜�ԍ�_�^�C�});
        PhPointer=new Other_Object(owner);


        MessageWnd = new Widget_Base(	own,
												MessageImage,
												0,
												�萔.��ʍ��� - ( MessageImage[0].getHeight() ),
												null);
		MessageWnd.setActiveToFade(true);
		Windows = this;

		GTimer.Initialize();
		GTimer.Stop_Timer();					// �{��p�[�g����n�܂�̂ŁA�ŏ����Ԃ͎~�܂��Ă���
    	GTimer.setActive(false);				// �Q�[���^�C�}
		
		Windows.add(MessageWnd);
		Windows.add(GTimer);

		
	    this.background = new ColorBackground(Color.black, �萔.��ʕ�, �萔.��ʍ���);
	    Windows.setBackground(this.background);
	    
	    Label_MesWnd = new Widget_Base(owner,"",0,0,MessageWnd);
	    MessageWnd.Mount_widget(Label_MesWnd, 0, 0);
	    Label_MesWnd.setActiveToFade(true);
	    
		/******************* ���b�Z�[�W�E�C���h�E�֘A ***********************/
	    this.MessageStringnow="";
	    this.MessageX = (int)MessageWnd.getX();
	    this.MessageY = (int)MessageWnd.getY();
	    this.MessageStringBuf = new StringBuffer(256);		// 256�����܂�
	    this.MessageStringnow = new String("");

		/********************* �琬���j���[�E�C���h�E�֘A ***********************/

	    menuwindow = new Widget_Base(	owner,
    									//owner.getImages("image/widget/menu.gif",1,1),
    									ImageLoader.Get_Instance(owner).getBufferedImage(�萔.�摜�ԍ�_���j��),
    									10,
    									100,
    									null
    									);
	    System.out.println("menuwindow->"+menuwindow.Get_Id());		//�ł΂���
    	// �`�F�b�N�{�^����ǉ�
    	CheckButton = new Widget_Base(	owner,
    									//owner.getImages("image/widget/check_b.gif",1,1),
    									ImageLoader.Get_Instance(owner).getBufferedImage(�萔.�摜�ԍ�_�`�F�b�N�{�^��),
    									0,0,
    									menuwindow);
	    System.out.println("CheckButton->"+CheckButton.Get_Id());		//�ł΂���


    	//	�V���b�v�{�^����ǉ�
    	ShopButton = new Widget_Base(	owner,
										//owner.getImages("image/widget/shop_b.gif",1,1),
    									ImageLoader.Get_Instance(owner).getBufferedImage(�萔.�摜�ԍ�_�V���b�v�{�^��),
										0,0,
										menuwindow);

    	//	�A�C�e���{�^����ǉ�
    	ItemButton = new Widget_Base(	owner,
										//owner.getImages("image/widget/item_b.gif",1,1),
    									ImageLoader.Get_Instance(owner).getBufferedImage(�萔.�摜�ԍ�_�A�C�e���{�^��),
										0,0,
										menuwindow);

    	//	�g���b�v�{�^����ǉ�
    	TrapButton = new Widget_Base(	owner,
										//owner.getImages("image/widget/trap_b.gif",1,1),
    									ImageLoader.Get_Instance(owner).getBufferedImage(�萔.�摜�ԍ�_�g���b�v�{�^��),
										0,0,
										menuwindow);

    	//	�r���h�{�^����ǉ�
    	BuildButton = new Widget_Base(	owner,
										//owner.getImages("image/widget/build_b.gif",1,1),
    									ImageLoader.Get_Instance(owner).getBufferedImage(�萔.�摜�ԍ�_�r���h�{�^��),
										0,0,
										menuwindow);
	    System.out.println("BuildButton->"+BuildButton.Get_Id());		//�ł΂���

    	//	�N���b�V���{�^����ǉ�
    	CrashButton = new Widget_Base(	owner,
										//owner.getImages("image/widget/crash_b.gif",1,1),
    									ImageLoader.Get_Instance(owner).getBufferedImage(�萔.�摜�ԍ�_�N���b�V���{�^��),
										0,0,
										menuwindow);

    	//	�f�[�^�{�^����ǉ�
    	DataButton = new Widget_Base(	owner,
										//owner.getImages("image/widget/data_b.gif",1,1),
    									ImageLoader.Get_Instance(owner).getBufferedImage(�萔.�摜�ԍ�_�f�[�^�{�^��),
										0,0,
										menuwindow);

    	//	�N�C�b�N�{�^����ǉ�
    	QuickButton = new Widget_Base(	owner,
										//owner.getImages("image/widget/quick_b.gif",1,1),
    									ImageLoader.Get_Instance(owner).getBufferedImage(�萔.�摜�ԍ�_�N�C�b�N�{�^��),
										0,0,
										menuwindow);

    	//	�ςɂ����{�^����ǉ�
    	PanishButton = new Widget_Base(	owner,
										//owner.getImages("image/widget/panish_b.gif",1,1),
    									ImageLoader.Get_Instance(owner).getBufferedImage(�萔.�摜�ԍ�_�p�j�b�V���{�^��),
										0,0,
										menuwindow);

    	//	���H�@���{�^����ǉ�
    	LoveButton = new Widget_Base(	owner,
										//owner.getImages("image/widget/love_b.gif",1,1),
    									ImageLoader.Get_Instance(owner).getBufferedImage(�萔.�摜�ԍ�_���u�{�^��),
										0,0,
										menuwindow);

    	/*menuwindow.Mount_widget(CheckButton, 3, 32);	//�`�F�b�N�{�^�����}�E���g
    	menuwindow.Mount_widget(ShopButton, 73, 32);	//�V���b�v�{�^�����}�E���g
    	menuwindow.Mount_widget(ItemButton,  3, 92);	//�A�C�e���{�^�����}�E���g
    	menuwindow.Mount_widget(TrapButton, 73, 92);	//�g���b�v�{�^�����}�E���g
    	menuwindow.Mount_widget(BuildButton, 3,152);	//�r���h�{�^�����}�E���g
    	menuwindow.Mount_widget(CrashButton,73,152);	//�N���b�V���{�^�����}�E���g
    	menuwindow.Mount_widget(DataButton,  3,212);	//�f�[�^�{�^�����}�E���g
    	menuwindow.Mount_widget(QuickButton,73,212);	//�N�C�b�N�{�^�����}�E���g
    	menuwindow.Mount_widget(PanishButton,3,272);	//�ςɂ����{�^�����}�E���g
    	menuwindow.Mount_widget(LoveButton, 73,272);	//���@�@�A�A�A���{�^�����}�E���g
       	*/
    	
    	menuwindow.Mount_widget(CheckButton, 3, 32);	//�`�F�b�N�{�^�����}�E���g
    	menuwindow.Mount_widget(ShopButton, 73, 32);	//�V���b�v�{�^�����}�E���g
    	menuwindow.Mount_widget(ItemButton,  3, 92);	//�A�C�e���{�^�����}�E���g
    	menuwindow.Mount_widget(TrapButton, 73, 92);	//�g���b�v�{�^�����}�E���g
    	menuwindow.Mount_widget(PanishButton,3,152);	//�ςɂ����{�^�����}�E���g
    	menuwindow.Mount_widget(LoveButton, 73,152);	//���@�@�A�A�A���{�^�����}�E���g
    	menuwindow.Mount_widget(DataButton,  3,212);	//�f�[�^�{�^�����}�E���g
    	menuwindow.Mount_widget(QuickButton,73,212);	//�N�C�b�N�{�^�����}�E���g
       	
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
		// ���j���[�E�C���h�E�͋N������A�N�e�B�u
		*/
    	menuwindow.setActiveToFade(false);
       	Windows.add(menuwindow);
	    /********************************************************************/
	
	    /***************** �E�C���h�E�n�̓X�N���[���s�v *********************/
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

       	/************************ build�E�C���h�Einitialize******************/
       	this.BuildWindow = new Widget_Build(	owner,
       											//owner.getImages("image/widget/window_build.GIF",1,1),
      											ImageLoader.Get_Instance(owner).getBufferedImage(�萔.�摜�ԍ�_�r���h�E�C���h�E),
      											15,15,
       											null);
       	this.BuildWindow_B_01_tree = new Widget_Build(	owner,
       													//owner.getImages("image/widget/window_build_B_01.GIF",1,1),
       													ImageLoader.Get_Instance(owner).getBufferedImage(�萔.�摜�ԍ�_�؃{�^��),
       													0,0,
       													BuildWindow,
       													�萔.�{�^��ID_�؃{�^��);
       	this.BuildWindow_B_02_house = new Widget_Build(	owner,
															//owner.getImages("image/widget/window_build_B_01.GIF",1,1),
															ImageLoader.Get_Instance(owner).getBufferedImage(�萔.�摜�ԍ�_�ƃ{�^��),
															0,0,
															BuildWindow,
															�萔.�{�^��ID_�ƃ{�^��);
       	this.BuildWindow_B_03_farm = new Widget_Build(	owner,
       														ImageLoader.Get_Instance(owner).getBufferedImage(�萔.�摜�ԍ�_�_�n�{�^��),
       														0,0,
       														BuildWindow,
       														�萔.�{�^��ID_�_�n�{�^��);

       	this.BuildWindow_B_99_exit = new Widget_Build(	owner,
       													//owner.getImages("image/widget/owaru.GIF",1,1),
       													ImageLoader.Get_Instance(owner).getBufferedImage(�萔.�摜�ԍ�_����{�^��),
       													0,0,
       													BuildWindow,
       													�萔.�{�^��ID_����{�^��);
       	this.BuildWindow_B_100_remove = new Widget_Build(	owner,
       														//owner.getImages("image/widget/tekkyo.GIF",1,1),
       														ImageLoader.Get_Instance(owner).getBufferedImage(�萔.�摜�ԍ�_�P���{�^��),
       														0,0,
       														BuildWindow,
       														�萔.�{�^��ID_�P���{�^��);
       	this.BuildWindow_L_01_cost = new Widget_Build(	owner,
       													"��p�@�F",
       													0,0,
       													BuildWindow,
       													�萔.���x��ID_���z_��p);
       	BuildWindow_L_01_cost.SetFontsize(15);		// �t�H���g�T�C�Y��ύX
       	this.BuildWindow_L_02_cash = new Widget_Build(	owner,
       													"�������F",
       													0,0,
       													BuildWindow,
       													�萔.���x��ID_���z_������);
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
       	
       	/************************ action�E�C���h�Einitialize******************/
        // �`�F�b�N�{�b�N�X�֘A�� Widget_Action.java�̒���rootwidget��p�Ƃ��Ē��ڏ������B
       	this.ActionWindow = new Widget_Action(	owner,
												//owner.getImages("image/widget/window_action.GIF",1,1),
												ImageLoader.Get_Instance(owner).getBufferedImage(�萔.�摜�ԍ�_�A�N�V�����E�C���h�E),
												0,0,
												null);
       	this.ActionWindow_B_99_exit = new Widget_Action(	owner,
															//owner.getImages("image/widget/owaru.GIF",1,1),
															ImageLoader.Get_Instance(owner).getBufferedImage(�萔.�摜�ԍ�_����{�^��),
															0,0,
															ActionWindow,
															�萔.�{�^��ID_����{�^��);
       	this.ActionWindow_L_01_syuzoku = new Widget_Action(	owner,
       															"",
       															0,0,
       															ActionWindow,
       															�萔.���x��ID_�A�N�V����_�푰��);
       	ActionWindow_L_01_syuzoku.SetFontsize(15);		// �t�H���g�T�C�Y����
       	this.ActionWindow_L_02_nougyou = new Widget_Action(	owner,
       															"",
       															0,0,
       															ActionWindow,
       															�萔.���x��ID_�A�N�V����_�_��);
       	ActionWindow_L_02_nougyou.SetFontsize(15);		// �t�H���g�T�C�Y����
       	this.ActionWindow_L_03_kari = new Widget_Action(		owner,
       															"",
       															0,0,
       															ActionWindow,
       															�萔.���x��ID_�A�N�V����_���);
       	ActionWindow_L_03_kari.SetFontsize(15);		// �t�H���g�T�C�Y����
       	this.ActionWindow_L_04_karicheck = new Widget_Action(	owner,
       															"���",
       															0,0,
       															ActionWindow,
       															�萔.���x��ID_�A�N�V����_����_���);
       	this.ActionWindow_L_05_nougyoucheck = new Widget_Action(	owner,
       																"�_��",
       																0,0,
       																ActionWindow,
       																�萔.���x��ID_�A�N�V����_����_�_��);
       	this.ActionWindow_L_06_asobicheck = new Widget_Action(	owner,
       															"�V��",
       															0,0,
       															ActionWindow,
       															�萔.���x��ID_�A�N�V����_����_�V��);
       	this.ActionWindow_O_01_image = new Widget_Action( 	owner,
       														(BufferedImage[])null,
       														0,0,
       														ActionWindow,
       														�萔.���̑�ID_�摜�\��);
       	
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
       	
       	/************************ status�E�C���h�Einitialize******************/
       	this.StatusWindow = new Widget_Status(	owner,
       											//owner.getImages("image/widget/window_status.GIF",1,1),
       											ImageLoader.Get_Instance(owner).getBufferedImage(�萔.�摜�ԍ�_�X�e�[�^�X�E�C���h�E),
       											0,0,
       											null);
       	this.StatusWindow_L_01_syuzoku = new Widget_Status(	owner,
       															"�푰",
       															0,0,
       															StatusWindow,
       															�萔.���x��ID_�X�e�[�^�X_�푰);
       	this.StatusWindow_L_01_syuzoku.SetFontsize(15);
       	this.StatusWindow_L_02_tairyoku =  new Widget_Status(	owner,
       															"������",
       															0,0,
       															StatusWindow,
       															�萔.���x��ID_�X�e�[�^�X_�̗�);

       	this.StatusWindow_L_02_tairyoku.SetFontsize(15);

       	this.StatusWindow_L_03_kokoro =  new Widget_Status(	owner,
       															"������",
       															0,0,
       															StatusWindow,
       															�萔.���x��ID_�X�e�[�^�X_������);
       	this.StatusWindow_L_03_kokoro.SetFontsize(15);

       	this.StatusWindow_L_04_chikara = new Widget_Status(	owner,
       														"������",
       														0,0,
       														StatusWindow,
       														�萔.���x��ID_�X�e�[�^�X_������);
       	this.StatusWindow_L_04_chikara.SetFontsize(15);

       	this.StatusWindow_L_05_amasa =  new Widget_Status(	owner,
       														"�Â�",
       														0,0,
       														StatusWindow,
       														�萔.���x��ID_�X�e�[�^�X_�Â�);
       	this.StatusWindow_L_05_amasa.SetFontsize(15);

       	this.StatusWindow_L_06_onaka =  new Widget_Status(	owner,
       														"���Ȃ�",
       														0,0,
       														StatusWindow,
       														�萔.���x��ID_�X�e�[�^�X_���Ȃ�);
       	this.StatusWindow_L_06_onaka.SetFontsize(15);

       	this.StatusWindow_B_01_tojiru = new Widget_Status(	owner,
       														//owner.getImages("image/widget/owaru.GIF",1,1),
       														ImageLoader.Get_Instance(owner).getBufferedImage(�萔.�摜�ԍ�_����{�^��),
       														0,0,
       														StatusWindow,
       														�萔.�{�^��ID_����{�^��);
       	this.StatusWindow_O_01_image = new Widget_Status( 	owner,
       														(BufferedImage[])null,
       														0,0,
       														StatusWindow,
       														�萔.���̑�ID_�摜�\��);
       	this.StatusWindow_L_06_days = new Widget_Status(	owner,
       														"����",
       														0,0,
       														StatusWindow,
       														�萔.���x��ID_�X�e�[�^�X_����);
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

       	/************************ ItemUse�E�C���h�Einitialize******************/
       	this.ItemUseWindow = new Widget_ItemUse(	owner,
       												//owner.getImages("image/widget/window_ItemUse.GIF",1,1),
       												ImageLoader.Get_Instance(owner).getBufferedImage(�萔.�摜�ԍ�_�A�C�e���E�C���h�E),
       												�萔.��ʕ�-140,10,
       												null);
       	this.ItemUseWindow_B_01 = new Widget_ItemUse(	owner,
       													(BufferedImage[])null,
       													0,0,
       													ItemUseWindow,
       													�萔.�{�^��ID_�A�C�e��01);
       	this.ItemUseWindow_B_10_tojiru = new Widget_ItemUse(	owner,
       															//owner.getImages("image/widget/owaru.GIF",1,1),
       															ImageLoader.Get_Instance(owner).getBufferedImage(�萔.�摜�ԍ�_����{�^��),
       															0,0,
       															ItemUseWindow,
       															�萔.�{�^��ID_����{�^��);
       	this.ItemUseWindow_B_01 = new Widget_ItemUse(	owner,
       													(BufferedImage[])null,
       													0,0,
       													ItemUseWindow,
       													�萔.�{�^��ID_�A�C�e��01);
       	this.ItemUseWindow_B_02 = new Widget_ItemUse(	owner,
					(BufferedImage[])null,
					0,0,
					ItemUseWindow,
					�萔.�{�^��ID_�A�C�e��02);
       	this.ItemUseWindow_B_03 = new Widget_ItemUse(	owner,
					(BufferedImage[])null,
					0,0,
					ItemUseWindow,
					�萔.�{�^��ID_�A�C�e��03);
       	this.ItemUseWindow_B_04 = new Widget_ItemUse(	owner,
					(BufferedImage[])null,
					0,0,
					ItemUseWindow,
					�萔.�{�^��ID_�A�C�e��04);
       	this.ItemUseWindow_B_05 = new Widget_ItemUse(	owner,
					(BufferedImage[])null,
					0,0,
					ItemUseWindow,
					�萔.�{�^��ID_�A�C�e��05);
       	this.ItemUseWindow_B_06 = new Widget_ItemUse(	owner,
					(BufferedImage[])null,
					0,0,
					ItemUseWindow,
					�萔.�{�^��ID_�A�C�e��06);

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
 
       	/******************* �_�C�A���O�E�C���h�E�֘A ***********************/
       	//�_�C�A���O�E�C���h�E�͕\�������̊֌W����A��ԍŌ�ɃC���X�^���X������
        DialogWnd = new Widget_Dialog(	own ,
												DialogImage,
												( �萔.��ʕ� / 2 ) - ( DialogImage[0].getWidth() / 2 ),
												�萔.��ʍ��� / 2 - ( DialogImage[0].getHeight() / 2 ),
												null,
												�萔.�{�^��ID_�k��);
		
		DialogWnd.setActive(false);
		DialogWnd.Set_Scrollable(false);
		Windows.add(DialogWnd);

       	/************* Comparator �Z�b�g **************/
    	this.setComparator(
    			new Comparator(){
    				public int compare(Object o1, Object o2){
    					Object_base	s1 = (Object_base) o1,
    								s2 = (Object_base) o2;
    					return (int)(s1.Get_Id() - s2.Get_Id() );
    				}
    			}
    	);
    	
    	/************* ���̃O���[�v�͔�A�N�e�B�u�ȃI�u�W�F�N�g���폜���Ȃ� **********/
    	this.getScanFrequence().setActive(false);

	}
	
	/******* �C���X�^���X�𓾂�(�e�C���X�^���X�ƈ�������) ********/
	public static synchronized Widget_Manager Get_Instance( yukkurisim_main own )
	{
		if( myself == null)
		{
			myself = new Widget_Manager(own , "WIDGET_MANAGER");
		}

		return myself;
	}
	
	//�@�\������X�v���C�g�O���[�v���擾����B
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
    	// �_�C�A���O�����ȊO�̓^�C�}�ɏ]������
    	if( GTimer.isMoving())	
    	{
	    	GTimer.Time_Inc();					//Timer��i�߂�
    	}
    	// �p�[�g�ύX���̕\���ΏۃE�C���h�E�ύX����
    	if( PhPointer.PhLaw.Get_Part()!=LastPart )
    	{
    		LastPart = PhPointer.PhLaw.Get_Part();
    		if(LastPart==�萔.�p�[�g_�{��_���z�敪)
    		{
    			// �{�􌚒z�p�[�g�̎��͌��z�E�C���h�E�̂݃A�N�e�B�u
    			this.closeAllWindow();	// ��x�S�ẴE�C���h�E�����
    			
    			this.BuildWindow.setActiveToFade(true);
    			this.ActionWindow.setActiveToFade(false);
    			this.menuwindow.setActiveToFade(false);
    			this.GTimer.setActiveToFade(false);
    			this.GTimer.Stop_Timer();
    			this.GTimer.Reset_Timer();	// �����ӁI���̍s�͎������ꎟ��P���p�[�g�̃u���b�N�ɓ���ׂ�
    		}
    		else if(LastPart==�萔.�p�[�g_�{��_�s���敪)
    		{
    			this.closeAllWindow();	// ��x�S�ẴE�C���h�E�����

    			this.BuildWindow.setActiveToFade(false);
    			this.ActionWindow.Reset_Cooperate_AVG_List();
    			this.ActionWindow.Reset_Hunting_AVG_List();
    			this.ActionWindow.setActiveToFade(true);
    			this.menuwindow.setActiveToFade(false);
    			this.GTimer.setActiveToFade(false);
    			this.GTimer.Stop_Timer();
    		}
    		else if(LastPart==�萔.�p�[�g_�琬�敪)
    		{
    			// �琬�p�[�g�̎��͈琬���j���[�y�у^�C�}���A�N�e�B�u
    			this.closeAllWindow();	// ��x�S�ẴE�C���h�E�����

    			this.BuildWindow.setActiveToFade(false);
    			this.ActionWindow.setActiveToFade(false);
    			this.menuwindow.setActiveToFade(true);
    			this.GTimer.setActiveToFade(true);
    			this.GTimer.Start_Timer();
    			
    		}
    		else if(LastPart==�萔.�p�[�g_�P���敪)
    		{
    			
    		}
    	}
    	this.MessageSpeedNow++;
	    	
		/* �����܂� */
		/*if ( testwindow.isClicked())
		{
		}*/
		/*else if ( owner.click()) 
        {
            testwindow.setActive(!testwindow.isActive());
        }*/

		// �`�F�b�N�{�^���������ꂽ��
		if ( CheckButton.isClicked() )
		{
			if(Widget_Manager.Get_Instance(owner).getMessenger()!=this)
			{
				Widget_Manager.Get_Instance(owner).ClsMessage();
				Widget_Manager.Get_Instance(owner).DrawMessage("�ǂ��̏�Ԃ�����́H", this);
			}
			this.BackupTimerStatus=GTimer.isMoving();
			GTimer.Stop_Timer();
			this.StatusWindow.setTargetting(true);
		}
		
		// �V���b�v�{�^���������ꂽ��
		if ( ShopButton.isClicked() )
		{
			Widget_Manager.Get_Instance(owner).Popup_Dialog_Window("�V���b�v�͖������ł��B");		// 17����
			System.out.println("�V���b�v�{�^��");
		}
		
		//�@�A�C�e���{�^���������ꂽ��
		if ( ItemButton.isClicked() )
		{
			this.ItemUseWindow.setActiveToFade(true);
		}
		//�@�g���b�v�{�^���������ꂽ��
		if ( TrapButton.isClicked() )
		{
			GameSaveLoader gamesaver = new GameSaveLoader();
			gamesaver.SaveState(owner);
			System.out.println("�Q�[�����Z�[�u���܂����B�@�I�����܂��B");
			System.exit(0);
		}    	
    	// �N���b�N���o��Manager�ōs���A��̓I�ȏ����͐V�����N���X���쐬���Ă����瑤�ɏ���
		//�@�r���h�{�^���������ꂽ��
		if ( BuildButton.isClicked() )
		{
			this.BuildWindow.setActiveToFade(true);				// �r���h�E�C���h�E���̂̃A�N�e�B�u��
		}
		//BuildWindow.update(elapsedTime);					// �r���h�E�C���h�E�̃A�b�v�f�[�g
		//ActionWindow.update(elapsedTime);
		super.update(elapsedTime);
    	this.doClickEvent();
    	
		
		//�@�N���b�V���}�����|���Ȃ�
		if ( CrashButton.isClicked() )
		{
			Widget_Manager.Get_Instance(owner).Popup_Dialog_Window("crash�͖������ł��B");		// 17����
		}

		//�@�f�[�^�{�^���������ꂽ��
		if ( DataButton.isClicked() )
		{
			Widget_Manager.Get_Instance(owner).Popup_Dialog_Window("data�͖������ł��B");		// 17����
		}

		//�@�N�C�b�N�{�^���������ꂽ��
		if ( QuickButton.isClicked() )
		{
			Widget_Manager.Get_Instance(owner).Popup_Dialog_Window("quick�͖������ł��B");		// 17����
		}

		//�@�ςɂ����{�^���������ꂽ��
		if ( PanishButton.isClicked() )
		{
			Widget_Manager.Get_Instance(owner).Popup_Dialog_Window("panish�͖������ł��B");		// 17����
		}
		//�@�b�E�E�E�E�E���D�@�[�[�[�D���{�^�� �������ꂽ��
		if ( LoveButton.isClicked() )
		{
			Widget_Manager.Get_Instance(owner).Popup_Dialog_Window("Love�͖������ł��B");		// 17����
		}
  
    	// �_�C�A���O���N���b�N���ꂽ��؂�
    	// �_�C�A���O���o�Ă���Ԃ͌����^�C�}�[���~����

    	/**************** �T�u�E�C���h�E�n ***********************/
       	//this.BuildWindow.update(elapsedTime);
       	//this.BuildWindow_B_01_tree.update(elapsedTime);

    	
	}
    
    /**
     * ���b�Z�[�W�E�C���h�E�ɕ�����\������.
     * calledobj�ɎQ�Ƃ�o�^���Ă����ƌ��ݕ\�����̃��b�Z�[�W���N�̂��̂��킩��
     * if���̔�r����r�I�����ɍs����i���ȁH�j
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
    	//�f�o�b�O�p�_�~�[�t�@���N�V����
    	
    }
    
    @Override
	public void render(Graphics2D g)
    {
    	super.render(g);
    	//���b�Z�[�W�E�C���h�E �f�[�^�\����
    	if(this.MessageStringBuf.length() != this.MessageStringnow.length())
    	{
    		if(this.MessageSpeedNow >= �萔.���b�Z�[�W�e�L�X�g���x)
    		{
    			this.MessageStringBuf.append(this.MessageStringnow.charAt(this.MessageStringBuf.length()));
    			this.MessageSpeedNow=0;
    		}
    	}
    	//System.out.println("���b�Z�[�W�f�o�b�O��"+this.MessageStringnow);
    	FontMan.drawString(g, this.MessageStringBuf.toString(), this.MessageX, this.MessageY);
    }
    
    /**
     * �S�ẴE�C���h�E����܂�
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
 * �����m �S���K�� �e�[�}�\���O
�u �����m�S���K���̃e�[�} �v
�@�@�쎌�@�C�z ���Y
�@�@��ȁ@�I�b�^�}�Q�r�b�`�E���^���c�����X�L�[

��S���S���S���I�@�S���S���S���I
�@����I �C������I ���z���N�̔w�����I
�@�����Ă���I �Q�����Ă��邼�I �N���X�S���K���I���E�r�b�`�I
�@���̗������@��]�������Ȃ�@��͂��������@���邢���낤
�@���̐��_���@�E�C�������Ȃ�@�N�̗܂́@�C�f�I���M�[
�@�͂Ă��Ȃ��I�@�͂Ă��Ȃ��I�@�S���K���I�@�S���K���I
 * 
 */