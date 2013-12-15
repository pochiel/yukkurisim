package subwindow;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import gamestatus.GameInfoManager;

import yukkurisim.Cursor_Manager;
import yukkurisim.Farm_Object;
import yukkurisim.Widget_Manager;
import yukkurisim.Widget_Base;
import yukkurisim.yukkurisim_main;
import yukkurisim.Tree_Object;
import yukkurisim.House_Object;

public class Widget_Build extends Widget_Base {
    private Cursor_Manager	OwnCursor;				// �J�[�\��
    private int ItemIndex = 0;						// ����I�����Ă��邩�H
    private int PageIndex = 1;						// ���y�[�W���Q�Ƃ��Ă��邩
    private String CostLabel;						// ��p���x���ŉ���\�����邩�H�e�������ێ�

    public Widget_Build(yukkurisim_main own, BufferedImage[] arg0) {
		super(own, arg0);
		Initialize();
		// TODO �����������ꂽ�R���X�g���N�^�[�E�X�^�u
	}

	public Widget_Build(yukkurisim_main own, double arg0, double arg1) {
		super(own, arg0, arg1);
		Initialize();
		// TODO �����������ꂽ�R���X�g���N�^�[�E�X�^�u
	}

	public Widget_Build(yukkurisim_main own, BufferedImage[] arg0, double x,
			double y) {
		super(own, arg0, x, y);
		Initialize();
		// TODO �����������ꂽ�R���X�g���N�^�[�E�X�^�u
	}

	public Widget_Build(yukkurisim_main own, BufferedImage[] arg0, double x,
			double y, Widget_Base parent) {
		super(own, arg0, x, y, parent);
		Initialize();
		// TODO �����������ꂽ�R���X�g���N�^�[�E�X�^�u
	}

	public Widget_Build(yukkurisim_main own, String message, double x,
			double y, Widget_Base parent) {
		super(own, message, x, y, parent);
		Initialize();
		// TODO �����������ꂽ�R���X�g���N�^�[�E�X�^�u
	}

	public Widget_Build(yukkurisim_main own, String message, double x,
			double y, Widget_Base parent,int mytype) {
		super(own, message, x, y, parent);
		this.Button_id=mytype;
		Initialize();
		// TODO �����������ꂽ�R���X�g���N�^�[�E�X�^�u
	}

	public Widget_Build(yukkurisim_main own, BufferedImage[] arg0, int x, int y, Widget_Build parent, int mytype) {
		super(own, arg0, x, y, parent);
		this.Button_id = mytype;
		Initialize();
	}
	
	@Override
	public void Initialize()
	{
		OwnCursor = Cursor_Manager.Get_Instance(owner);		// �J�[�\��

		// �����A����܂�X�}�[�g�ȃR�[�h����Ȃ��Ǝv�����ǁE�E�E�B		
		if(this.Get_Parent()==null)
		{
			CostLabel = "��p�@�F";
		}
	}
	
	@Override
	public void doOnMouseEvent()
	{
		if(Button_id==�萔.�{�^��ID_�؃{�^��)
		{
			if(Widget_Manager.Get_Instance(owner).getMessenger()!=this)
			{
				Widget_Manager.Get_Instance(owner).ClsMessage();
				Widget_Manager.Get_Instance(owner).DrawMessage("�����������ȃ����S�̐���؂ł��B", this);
				((Widget_Build)this.Get_Root_Parent()).setCostLabel("��p�@�F" + GameInfoManager.Get_Instance().getCost(�萔.TYPE_��object));
			}
		}
		else if( Button_id==�萔.�{�^��ID_�ƃ{�^��)
		{
			if(Widget_Manager.Get_Instance(owner).getMessenger()!=this)
			{
				Widget_Manager.Get_Instance(owner).ClsMessage();
				Widget_Manager.Get_Instance(owner).DrawMessage("������肽���̏Z�ނ������ł��B", this);
				((Widget_Build)this.Get_Root_Parent()).setCostLabel("��p�@�F" + GameInfoManager.Get_Instance().getCost(�萔.TYPE_��object));
			}			
		}
		else if( Button_id==�萔.�{�^��ID_�_�n�{�^��)
		{
			if(Widget_Manager.Get_Instance(owner).getMessenger()!=this)
			{
				Widget_Manager.Get_Instance(owner).ClsMessage();
				Widget_Manager.Get_Instance(owner).DrawMessage("������肽���̔_���ł��B", this);
				((Widget_Build)this.Get_Root_Parent()).setCostLabel("��p�@�F" + GameInfoManager.Get_Instance().getCost(�萔.TYPE_�_�nobject));
			}			
		}
		else
		{
			((Widget_Build)this.Get_Root_Parent()).setCostLabel("��p�@�F");
		}
	}
	
	@Override
	public void doClickEvent()
	{
		//System.out.println("Widgetbuild��doclickevent");
		super.doClickEvent();

		if(Button_id==�萔.�{�^��ID_�؃{�^��)
		{
			System.out.println("�؃{�^�������ꂽ��");
			OwnCursor.SetCursorActivate(this, true, �萔.�J�[�\������_�r���h�E�C���h�E01_��,1,1);	// �J�[�\����\��
			this.Get_Root_Parent().setActiveToFade(false);	// �r���h�E�C���h�E���̂̃C���A�N�e�B�x�[�g
		}
		else if( Button_id==�萔.�{�^��ID_�ƃ{�^��)
		{
			System.out.println("�ƃ{�^�������ꂽ��");
			OwnCursor.SetCursorActivate(this, true, �萔.�J�[�\������_�r���h�E�C���h�E01_��,3,2);	// �J�[�\����\��
			this.Get_Root_Parent().setActiveToFade(false);	// �r���h�E�C���h�E���̂̃C���A�N�e�B�x�[�g
			
		}
		else if( Button_id==�萔.�{�^��ID_�_�n�{�^��)
		{
			System.out.println("�_�n�{�^�������ꂽ��");
			OwnCursor.SetCursorActivate(this, true, �萔.�J�[�\������_�r���h�E�C���h�E01_�_�n,1,1);	// �J�[�\����\��
			this.Get_Root_Parent().setActiveToFade(false);	// �r���h�E�C���h�E���̂̃C���A�N�e�B�x�[�g
			
		}
		else if( Button_id==�萔.�{�^��ID_����{�^��)
		{
			System.out.println("����{�^��");
			this.Get_Root_Parent().setActiveToFade(false);	// �r���h�E�C���h�E���̂̃C���A�N�e�B�x�[�g
			PhLaw.Change_Part();						// �p�[�g�ړ�
			this.setActiveToFade(false);
		}
		else if( Button_id==�萔.�{�^��ID_�P���{�^�� )
		{
			Widget_Manager.Get_Instance(owner).Popup_Dialog_Window("�P���{�^���͖������ł��B");		// 17����
			System.out.println("�P���{�^��");
		}
		else
		{
			System.out.println("�����ꂽ���ǋ@�{�^������Ȃ�����"+Button_id);			

		}
	}
    @Override
	public void update(long elapsedTime)
    {
    	if(Button_id==�萔.���x��ID_���z_������)
    	{	//�������\��
    		this.Set_Mymessage("�������F"+GameInfoManager.Get_Instance().GetCash()+"�~");
    	}
    	if(Button_id==�萔.���x��ID_���z_��p)
    	{
    		// �e�{�^����OnMouseRide�C�x���g����̔�p���b�Z�[�W�̎󂯓n����
    		// root�e���o�R���čs���B�i�L���`�����C�R�[�h 2010�O�����v��
    		this.Set_Mymessage(((Widget_Build)this.Get_Root_Parent()).getCostLabel());
    	}
    	super.update(elapsedTime);
    }

    @Override
	public void render(Graphics2D g) {
    	super.render(g);

    	if(this.ItemIndex!=0)
    	{
    		// ���炩�̃A�C�e�����I������Ă����
    		
    	}
    }

	@Override
	public void Comeback_From_Cursor(int x,int y,int s)
	{
		GameInfoManager gman = GameInfoManager.Get_Instance();

		System.out.println("widget_build�ɖ߂��Ă��܂����Bx->"+x+"  y->"+y);
		if( s==�萔.�J�[�\������_�r���h�E�C���h�E01_�� )
		{
			if(this.PhLaw.Get_TileManager().Check_able_to_Build(x, y,1,1))
			{
				if(gman.getCost(�萔.TYPE_��object)>gman.GetCash())
				{
					Widget_Manager.Get_Instance(owner).ClsMessage();
					Widget_Manager.Get_Instance(owner).DrawMessage("����������Ȃ���I",this);					
				}
				else
				{
					gman.SetCash( gman.GetCash() - gman.getCost(�萔.TYPE_��object) );	// ����������
					PhLaw.Get_ObjectManager().add(new Tree_Object(owner,x,y),�萔.TYPE_��object);
				}
			}
			else
			{
				Widget_Manager.Get_Instance(owner).ClsMessage();
				Widget_Manager.Get_Instance(owner).DrawMessage("�����ɂ͐ݒu�ł��Ȃ���I",this);
			}			
		}
		else if( s==�萔.�J�[�\������_�r���h�E�C���h�E01_�_�n )
		{
			if(this.PhLaw.Get_TileManager().Check_able_to_Build(x, y,1,1))
			{
				if(gman.getCost(�萔.TYPE_�_�nobject)>gman.GetCash())
				{
					Widget_Manager.Get_Instance(owner).ClsMessage();
					Widget_Manager.Get_Instance(owner).DrawMessage("����������Ȃ���I",this);					
				}
				else
				{
					gman.SetCash( gman.GetCash() - gman.getCost(�萔.TYPE_�_�nobject) );	// ����������
					PhLaw.Get_ObjectManager().add(new Farm_Object(owner,x,y),�萔.TYPE_�_�nobject);
				}
			}
			else
			{
				Widget_Manager.Get_Instance(owner).ClsMessage();
				Widget_Manager.Get_Instance(owner).DrawMessage("�����ɂ͐ݒu�ł��Ȃ���I",this);
			}			
		}
		else if ( s==�萔.�J�[�\������_�r���h�E�C���h�E01_�� )
		{
			if(this.PhLaw.Get_TileManager().Check_able_to_Build(x, y, 3, 2))
			{
				if(gman.getCost(�萔.TYPE_��object)>gman.GetCash())
				{
					Widget_Manager.Get_Instance(owner).ClsMessage();
					Widget_Manager.Get_Instance(owner).DrawMessage("����������Ȃ���I",this);										
				}
				else
				{
					gman.SetCash( gman.GetCash() - gman.getCost(�萔.TYPE_��object) );	// ����������
					PhLaw.Get_ObjectManager().add(new House_Object(owner,x,y),�萔.TYPE_��object); 	// HouseObject���ōs��
				}
			}
			else
			{
				Widget_Manager.Get_Instance(owner).ClsMessage();
				Widget_Manager.Get_Instance(owner).DrawMessage("�����ɂ͐ݒu�ł��Ȃ���I",this);
			}
		}
		this.Get_Root_Parent().setActiveToFade(true);	// �r���h�E�C���h�E���̂̃A�N�e�B�x�[�g

	}

	public String getCostLabel() {
		return CostLabel;
	}

	public void setCostLabel(String costLabel) {
		CostLabel = costLabel;
	}
}
