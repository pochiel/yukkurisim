package subwindow;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Comparator;

import com.golden.gamedev.object.Sprite;

import yukkurisim.Object_base;
import yukkurisim.Physical_Law_Facade;
import yukkurisim.Widget_Base;
import yukkurisim.Widget_Checkbox;
import yukkurisim.Widget_Manager;
import yukkurisim.yukkurisim_main;
import yukkurisim.yukkuri_base;
import yukkurisim.ADV_SpriteGroup_base;
import yukkurisim.ImageLoader;
import gamestatus.GameInfoManager;
import gamestatus.SyuzokuInfoManager;

public class Widget_Action extends Widget_Base {
	private int[] Syuzoku_List;
	private int SyuzokuIndex = 0;
	private int[] Cooperative_List;		// �������z��
	private int[] Hunting_List;			// ���\�͔z��
	private SyuzokuInfoManager �푰 = new SyuzokuInfoManager();
	
	private ADV_SpriteGroup_base Checkboxs;
    private Widget_Checkbox	ActionWindow_C_01_kari;	// �`�F�b�N�{�b�N�X�P_���
    private Widget_Checkbox	ActionWindow_C_02_farm;	// �`�F�b�N�{�b�N�X�P_�_��
    private Widget_Checkbox	ActionWindow_C_03_play;	// �`�F�b�N�{�b�N�X�P_�V��
    private boolean			Last_State[] = {false,false,false};

	/** ���̑��摜�f�[�^�\���p�摜�f�[�^�Q�� **/
	private BufferedImage[] reimu_image = null;		// �ꂢ�ނ̃C���[�W
	private BufferedImage[] marisa_image = null;		// �܂肳�̃C���[�W

	public Widget_Action(yukkurisim_main own) {
		super(own);
		Initialize();
	}

	public Widget_Action(yukkurisim_main own, BufferedImage[] arg0) {
		super(own, arg0);
		Initialize();
	}

	public Widget_Action(yukkurisim_main own, double arg0, double arg1) {
		super(own, arg0, arg1);
		Initialize();
	}

	public Widget_Action(yukkurisim_main own, BufferedImage[] arg0, double x,
			double y) {
		super(own, arg0, x, y);
		Initialize();
	}

	public Widget_Action(yukkurisim_main own, BufferedImage[] arg0, double x,
			double y, Widget_Base parent) {
		super(own, arg0, x, y, parent);
		Initialize();
	}

	public Widget_Action(yukkurisim_main own, String message, double x,
			double y, Widget_Base parent) {
		super(own, message, x, y, parent);
		Initialize();
	}
	
	/* �ʏ�p����R���X�g���N�^�͂��� */
	public Widget_Action(yukkurisim_main own, BufferedImage[] arg0, double x,
		double y, Widget_Base parent,int mytype) {
		super(own, arg0, x, y, parent);
		this.Button_id = mytype;
		Initialize();
	}
	
	public Widget_Action(yukkurisim_main own,
			String message,
			double x,
			double y ,
			Widget_Base parent,
			int mytype)
	{
		super(own,message,x,y,parent,mytype);
		Initialize();
	}
	@Override
	public void doClickEvent()
	{
		System.out.println("����WidgetAction��doclickevent");
	
		super.doClickEvent();
		
		if(Button_id==�萔.�{�^��ID_����{�^��)
		{
			if( ((Widget_Action)this.Get_Root_Parent()).Get_Checkbox_Propery()==-1 )		// �`�F�b�N�{�b�N�X�������������Ă����ԂłȂ�
			{
				Widget_Manager.Get_Instance(owner).Popup_Dialog_Window("�ǂꂩ���I�����Ă��������B");
				return;
			}
			else if(((Widget_Action)this.Get_Root_Parent()).Get_Checkbox_Propery()==1)
			{
				// ���
				GameInfoManager.Get_Instance().setPlayPartState(SyuzokuIndex,�萔.�A�N�V����_���);
				this.Get_Root_Parent().setActiveToFade(false);	// �s���E�C���h�E���̂̃C���A�N�e�B�x�[�g
				PhLaw.Change_Part();						// �p�[�g�ړ�
				this.setActiveToFade(false);		
			}
			else if(((Widget_Action)this.Get_Root_Parent()).Get_Checkbox_Propery()==2)
			{
				boolean farmMaded = false;
				
				// �_�n������Ă��邩�`�F�b�N
				for(int i=0;i<�萔.��ʉ��Z����;i++)
				{
					for(int j=0;j<�萔.��ʏc�Z����;j++)
					{
						if(Physical_Law_Facade.Get_Instance(owner).Get_ObjectManager().Get_obj_type(i, j)==�萔.TYPE_�_�nobject)
						{
							farmMaded = true;
						}
					}
				}
				
				//�@�_�n�����łɍ���Ă����
				if( farmMaded )
				{
					GameInfoManager.Get_Instance().setPlayPartState(SyuzokuIndex,�萔.�A�N�V����_�_��);
					// �_��
					this.Get_Root_Parent().setActiveToFade(false);	// �s���E�C���h�E���̂̃C���A�N�e�B�x�[�g
					PhLaw.Change_Part();						// �p�[�g�ړ�
					this.setActiveToFade(false);
				}
				else
				{
					Widget_Manager.Get_Instance(owner).Popup_Dialog_Window("�_�n��ݒu���Ă��������I");
					return;
				}
			}
			else if(((Widget_Action)this.Get_Root_Parent()).Get_Checkbox_Propery()==3)
			{
				// �v���C�J�n
				GameInfoManager.Get_Instance().setPlayPartState(SyuzokuIndex,�萔.�A�N�V����_�V��);
				this.Get_Root_Parent().setActiveToFade(false);	// �s���E�C���h�E���̂̃C���A�N�e�B�x�[�g
				PhLaw.Change_Part();						// �p�[�g�ړ�
				this.setActiveToFade(false);
			}

		}
	}

	/**
	 * �s���I���E�C���h�E�Ŏg�p�����`�F�b�N�{�b�N�X��
	 * �������i ������I������Ă���j��Ԃɂ��邩�`�F�b�N
	 * @return �e�`�F�b�N�{�b�N�X�̐��l��Ԃ��B
	 * �P�����@�Q���_�Ɓ@�R���V�ԁ@�|�P���s��
	 */
	public int Get_Checkbox_Propery()
	{
		if(this.Get_Parent()!=null)
		{	// �e�E�C���h�E�ȊO�Ŏ��s���ꂽ
			return -1;
		}
		else
		{
			int ans = ActionWindow_C_01_kari.Get_Value()?1:0;
			ans += ActionWindow_C_02_farm.Get_Value()?1:0;
			ans += ActionWindow_C_03_play.Get_Value()?1:0;

			if(ans==1)
			{
				if(ActionWindow_C_01_kari.Get_Value())
				{
					return 1;
				}
				else if(ActionWindow_C_02_farm.Get_Value())
				{
					return 2;
				}
				else
				{
					return 3;
				}
			}
			else
			{
				return -1;
			}
		}
	}
	
	/**
	 * �����Ƃ��Ďw�肳�ꂽ�`�F�b�N�{�b�N�X�ȊO�̃`�F�b�N�{�b�N�X��false�Ƃ��܂��B
	 * ���[�g�E�C���h�E�ȊO�ŌĂяo���Ă��Ӗ��Ȃ��ł��B
	 * @param c
	 */
	public void Set_Onlyone_Checkbox()
	{
		if(this.Get_Parent()==null)
		{
			boolean tmp[]=new boolean[3];
			tmp[0]=ActionWindow_C_01_kari.Get_Value();
			tmp[1]=ActionWindow_C_02_farm.Get_Value();
			tmp[2]=ActionWindow_C_03_play.Get_Value();
			
			if(	!(
					(tmp[0]==Last_State[0])&&
					(tmp[1]==Last_State[1])&&
					(tmp[2]==Last_State[2])
				)	)	// �S�Ĉ�v���Ȃ��i�ǂ���������Ă���ꍇ�j
			{
				// �r���I�_���a���i�[
				//System.out.println("last��"+Last_State[0]+Last_State[1]+Last_State[2]+"  tmp��"+tmp[0]+tmp[1]+tmp[2]);
				ActionWindow_C_01_kari.Set_Value(tmp[0]^Last_State[0]);
				ActionWindow_C_02_farm.Set_Value(tmp[1]^Last_State[1]);
				ActionWindow_C_03_play.Set_Value(tmp[2]^Last_State[2]);
				
				Last_State[0]=ActionWindow_C_01_kari.Get_Value();
				Last_State[1]=ActionWindow_C_02_farm.Get_Value();
				Last_State[2]=ActionWindow_C_03_play.Get_Value();
					
			}
		}
	}
	
	@Override
	public void Initialize()
	{
		this.Syuzoku_List = new int[�萔.TYPE_�L�����N�^�[�n�^�C�v����];
		this.Cooperative_List = new int[�萔.TYPE_�L�����N�^�[�n�^�C�v����];
		this.Hunting_List = new int[�萔.TYPE_�L�����N�^�[�n�^�C�v����];
		if(this.Get_Parent()==null)
		{
			this.ResetSyuzokuList();			// �푰���X�g�̃��Z�b�g(���[�g�̂�)
			this.Reset_Cooperate_AVG_List();	// ���������σ��X�g�̃��Z�b�g
			this.Reset_Hunting_AVG_List();		// ��̔\�͂̃��Z�b�g
			
	       	this.ActionWindow_C_01_kari = new Widget_Checkbox(		owner,this);
	       	this.ActionWindow_C_02_farm = new Widget_Checkbox(		owner,this);
	       	this.ActionWindow_C_03_play = new Widget_Checkbox(		owner,this);
	       	this.Checkboxs = new ADV_SpriteGroup_base(owner,"WIDGET_ACTION_CHECKBOX");
	       	this.Mount_widget(ActionWindow_C_01_kari, 20, 120);
	       	this.Mount_widget(ActionWindow_C_02_farm, 100, 120);
	       	this.Mount_widget(ActionWindow_C_03_play, 180, 120);
	       	this.Checkboxs.add(ActionWindow_C_01_kari);
	       	this.Checkboxs.add(ActionWindow_C_02_farm);
	       	this.Checkboxs.add(ActionWindow_C_03_play);
	       	
	       	/************* Comparator �Z�b�g **************/
	    	this.Checkboxs.setComparator(
	    			new Comparator(){
	    				public int compare(Object o1, Object o2){
	    					Object_base	s1 = (Object_base) o1,
	    								s2 = (Object_base) o2;
	    					return (int)(s1.Get_Id() - s2.Get_Id() );
	    				}
	    			}
	    	);
	    	
	    	/************* ���̃O���[�v�͔�A�N�e�B�u�ȃI�u�W�F�N�g���폜���Ȃ� **********/
	    	this.Checkboxs.getScanFrequence().setActive(false);

		}
		else
		{
			this.Syuzoku_List=((Widget_Action)this.Get_Root_Parent()).Get_SyuzokuList();			
			this.Cooperative_List=((Widget_Action)this.Get_Root_Parent()).Get_Cooperate_AVG_List();
			this.Hunting_List=((Widget_Action)this.Get_Root_Parent()).Get_Hunting_AVG_List();
			this.ResetIndex();
		}
		// ���̑��摜�\���p�摜�f�[�^����������
		if(Button_id==�萔.���̑�ID_�摜�\��)
		{
			//this.reimu_image = owner.getImages("image/reimuwait.gif", 1, 6);
			this.reimu_image = ImageLoader.Get_Instance(owner).getBufferedImage(�萔.�摜�ԍ�_�������ꂢ�ޑ҂�);
			
			this.setAnimate(true);				// �A�j���[�V����������
			this.setLoopAnim(true);			// ���[�v������
		}
	}

	/**
	 * �푰���X�g�����Z�b�g���܂��B
	 * �����̓��[�g�E�B�W�F�b�g�̂ݍs���B
	 */
	private void ResetSyuzokuList()
	{
		// ���[�g�E�B�W�F�b�g�̂�
		if(this.Get_Parent()==null)
		{
			Sprite[]	msprites = PhLaw.Get_ObjectManager().getSprites();
			int			msize = PhLaw.Get_ObjectManager().getSize();
			Object_base temp;
	
			for(int i = 0;i<�萔.TYPE_�L�����N�^�[�n�^�C�v����;i++)
			{
				// ������
				this.Syuzoku_List[i] = 0;
			}
	
			for(int i=(msize-1);i>=0;i--){
				temp = (Object_base)msprites[i];
				
				if( temp.isActive()&&(temp.Get_Type()<�萔.TYPE_object_�L�����N�^�ƌ��z���ԍ��~���l)){
					this.Syuzoku_List[temp.Get_Type()-100]++;	// �푰���ʒ萔��100����X�^�[�g
				}
			}
		}
	}
	/**
	 * 
	 */
	private void ResetIndex()
	{
		int i = 0;
		while(this.Syuzoku_List[i]>0)
		{
			this.SyuzokuIndex=i;
			break;
		}
	}
	
	/*
	 * �푰���X�g��Ԃ��B null��Ԃ����ꍇ�A�Q�Ƃ����������B
	 */
	public int[] Get_SyuzokuList()
	{
		if(this.Get_Parent()==null)
		{
			this.ResetSyuzokuList();
			return this.Syuzoku_List;
		}
		return null;
	}
	
	/**
	 * �������̕��ς����߂�
	 * @return
	 */
	public int[] Get_Cooperate_AVG_List()
	{
		if(this.Get_Parent()==null)
		{
			this.Reset_Cooperate_AVG_List();
			return this.Cooperative_List;
		}
		return null;
		
	}
	
	public void Reset_Cooperate_AVG_List()
	{
		// ���[�g�E�B�W�F�b�g�̂�
		if(this.Get_Parent()==null)
		{
			Sprite[]	msprites = PhLaw.Get_ObjectManager().getSprites();
			int			msize = PhLaw.Get_ObjectManager().getSize();
			int[] num_cnt = new int[�萔.TYPE_�L�����N�^�[�n�^�C�v����];
			yukkuri_base temp;
	
			for(int i = 0;i<�萔.TYPE_�L�����N�^�[�n�^�C�v����;i++)
			{
				// ������
				this.Cooperative_List[i] = 0;
			}
	
			for(int i=(msize-1);i>=0;i--){
				if( ((Object_base)msprites[i]).isCharactor())
				{
					temp = (yukkuri_base)msprites[i];
					
					if(temp.isActive()&&(temp.Get_Type()<�萔.TYPE_object_�L�����N�^�ƌ��z���ԍ��~���l)){
						// �푰���ʒ萔��100����X�^�[�g
						num_cnt[temp.Get_Type()-100]++;
						this.Cooperative_List[temp.Get_Type()-100]+=temp.Get_Cooperative();	
					}
				}
			}
			
			for(int i = 0;i<�萔.TYPE_�L�����N�^�[�n�^�C�v����;i++)
			{
				if(num_cnt[i]!=0)
				{
					Cooperative_List[i] = Cooperative_List[i]/num_cnt[i];
				}
			}
		}
	}

	/**
	 * �������̕��ς����߂�
	 * @return
	 */
	public int[] Get_Hunting_AVG_List()
	{
		if(this.Get_Parent()==null)
		{
			this.Reset_Hunting_AVG_List();
			return this.Hunting_List;
		}
		return null;
		
	}
	
	public void Reset_Hunting_AVG_List()
	{
		// ���[�g�E�B�W�F�b�g�̂�
		if(this.Get_Parent()==null)
		{
			Sprite[]	msprites = PhLaw.Get_ObjectManager().getSprites();
			int			msize = PhLaw.Get_ObjectManager().getSize();
			int[] 		num_cnt = new int[�萔.TYPE_�L�����N�^�[�n�^�C�v����];
			yukkuri_base temp;
	
			for(int i = 0;i<�萔.TYPE_�L�����N�^�[�n�^�C�v����;i++)
			{
				// ������
				this.Hunting_List[i] = 0;
			}
	
			for(int i=(msize-1);i>=0;i--){
				if( ((Object_base)msprites[i]).isCharactor())
				{
					temp = (yukkuri_base)msprites[i];
					
					if(temp.isActive()&&(temp.Get_Type()<�萔.TYPE_object_�L�����N�^�ƌ��z���ԍ��~���l)){
						// �푰���ʒ萔��100����X�^�[�g
						num_cnt[temp.Get_Type()-100]++;
						this.Hunting_List[temp.Get_Type()-100]+=temp.Get_Hunting();	
					}
				}
			}
			
			for(int i = 0;i<�萔.TYPE_�L�����N�^�[�n�^�C�v����;i++)
			{
				if(num_cnt[i]!=0)
				{
					Hunting_List[i] = Hunting_List[i]/num_cnt[i];
				}
			}
		}
	}

	
	
	@Override
	public void update(long elapsedTime)
    {
		/*if(Button_id==�萔.�{�^��ID_����{�^��)
		{
			/** �f�o�b�O **/
			/*System.out.println("���f�o�b�O��"+this.isClicked());
		}*/
		if(this.Get_Parent()==null)
		{
			this.Checkboxs.update(elapsedTime);
			this.Checkboxs.doClickEvent();
    		Set_Onlyone_Checkbox();
		}
    	if(Button_id==�萔.���x��ID_�A�N�V����_�푰��)
    	{
    		this.Set_Mymessage("���ゼ���@�F"+�푰.Get_SyuzokuName(SyuzokuIndex+100)+"�̂ւ�����");
    	}
    	else if(Button_id==�萔.���x��ID_�A�N�V����_�_��)
    	{
    		this.Set_Mymessage("�̂����傤�F"+�푰.Get_rank_Cooperative(Cooperative_List[SyuzokuIndex]));
    	}
    	else if(Button_id==�萔.���x��ID_�A�N�V����_���)
    	{
    		this.Set_Mymessage("����@�@�@�F"+�푰.Get_rank_kari(Hunting_List[SyuzokuIndex]));
    	}
    	else if(Button_id==�萔.���̑�ID_�摜�\��)
    	{
    		if((SyuzokuIndex+100==�萔.TYPE_�������object)&&(this.getImages()!=reimu_image))
    		{
    			this.setImages(reimu_image);
    		}
    	}
    	super.update(elapsedTime);
    }
	
    @Override
	public void render(Graphics2D g)
    {
    	super.render(g);
    	if(this.Get_Parent()==null)
    	{
    		this.Checkboxs.render(g);
    	}
    	//System.out.println("this��"+this+"  this.alp��"+this.alpha+"   this.isact��"+this.isActive());
    }
    
    @Override
	public void doOnMouseEvent()
    {
    	if(Widget_Manager.Get_Instance(owner).getMessenger()!=this)
    	{
    		if(Button_id==�萔.���x��ID_�A�N�V����_����_���)
    		{
    			Widget_Manager.Get_Instance(owner).ClsMessage();
    			Widget_Manager.Get_Instance(owner).DrawMessage("���̎푰�����ɏo����B���������Ă��邩���H", this);
    		}
    		else if(Button_id==�萔.���x��ID_�A�N�V����_����_�_��)
        	{
    			Widget_Manager.Get_Instance(owner).ClsMessage();
    			Widget_Manager.Get_Instance(owner).DrawMessage("���̎푰�ɔ��̐��b�������悤�B����������؂��ł��邩�ȁH", this);
        	}
        	else if(Button_id==�萔.���x��ID_�A�N�V����_����_�V��)
        	{
    			Widget_Manager.Get_Instance(owner).ClsMessage();
    			Widget_Manager.Get_Instance(owner).DrawMessage("���̎푰�͍����͋x�݂��I�������V��ł����ĂˁI", this);
        	}
    	}
    }
}
