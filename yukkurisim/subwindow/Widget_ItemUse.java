package subwindow;

import java.awt.image.BufferedImage;

import yukkurisim.Widget_Base;
import yukkurisim.Widget_Manager;
import yukkurisim.yukkurisim_main;
import yukkurisim.ItemManager;
import yukkurisim.ImageLoader;

public class Widget_ItemUse extends Widget_Base {
	private int PageIndex;
	private BufferedImage[] myItemlist;		// �\���ʒu�ɂ��ăy�[�W�ʂɉ���\�����邩�H
	private BufferedImage[] HatenaImage;		// ���m�̃A�C�e���\��
	private final int MAXPAGE = 3;			// �y�[�W�}�b�N�X
	
	public Widget_ItemUse(yukkurisim_main own) {
		super(own);
		Initialize();
	}

	public Widget_ItemUse(yukkurisim_main own, BufferedImage[] arg0) {
		super(own, arg0);
		Initialize();
	}

	public Widget_ItemUse(yukkurisim_main own, double arg0, double arg1) {
		super(own, arg0, arg1);
		Initialize();
	}

	public Widget_ItemUse(yukkurisim_main own, BufferedImage[] arg0, double x,
			double y) {
		super(own, arg0, x, y);
		Initialize();
	}

	public Widget_ItemUse(yukkurisim_main own, BufferedImage[] arg0, double x,
			double y, Widget_Base parent) {
		super(own, arg0, x, y, parent);
		Initialize();
	}

	public Widget_ItemUse(yukkurisim_main own, String message, double x,
			double y, Widget_Base parent) {
		super(own, message, x, y, parent);
		Initialize();
	}

	public Widget_ItemUse(yukkurisim_main own, BufferedImage[] arg0, int x,
			int y, Widget_Base parent, int mytype) {
		super(own, arg0, x, y, parent, mytype);
		Initialize();
	}

	public Widget_ItemUse(yukkurisim_main own, String message, double x,
			double y, Widget_Base parent, int mytype) {
		super(own, message, x, y, parent, mytype);
		Initialize();
	}

	@Override
	public void Initialize()
	{
		if(this.Get_Parent()==null)
		{
			//�e�E�C���h�E������
			this.setPageIndex(0);		//1�y�[�W��
		}
		else
		{
			if(this.Get_Type()==�萔.�{�^��ID_�A�C�e��01)
			{
				this.myItemlist=new BufferedImage[MAXPAGE];
				this.myItemlist[0]=owner.getImage("item/food1.gif");
				this.myItemlist[1]=owner.getImage("item/book.gif");
				this.myItemlist[2]=owner.getImage("item/kanameishi.gif");
				this.setImages(myItemlist);
			}
			else if(this.Get_Type()==�萔.�{�^��ID_�A�C�e��02)
			{
				this.myItemlist=new BufferedImage[MAXPAGE];
				this.myItemlist[0]=owner.getImage("item/food2.gif");
				this.myItemlist[1]=owner.getImage("item/onmyoudan.gif");
				this.myItemlist[2]=owner.getImage("item/black.gif");				
				this.setImages(myItemlist);
			}
			else if(this.Get_Type()==�萔.�{�^��ID_�A�C�e��03)
			{
				this.myItemlist=new BufferedImage[MAXPAGE];
				this.myItemlist[0]=owner.getImage("item/ponde.gif");
				this.myItemlist[1]=owner.getImage("item/houki.gif");
				this.myItemlist[2]=owner.getImage("item/black.gif");			
				this.setImages(myItemlist);
			}
			else if(this.Get_Type()==�萔.�{�^��ID_�A�C�e��04)
			{
				this.myItemlist=new BufferedImage[MAXPAGE];
				this.myItemlist[0]=owner.getImage("item/ponde.gif");
				this.myItemlist[1]=owner.getImage("item/scisser.gif");	// �V���[�N���[���̉摜���Ȃ�
				this.myItemlist[2]=owner.getImage("item/black.gif");
				this.setImages(myItemlist);
			}
			else if(this.Get_Type()==�萔.�{�^��ID_�A�C�e��05)
			{
				this.myItemlist=new BufferedImage[MAXPAGE];
				this.myItemlist[0]=owner.getImage("item/orangejuice.gif");
				this.myItemlist[1]=owner.getImage("item/kushi.gif");
				this.myItemlist[2]=owner.getImage("item/black.gif");				
				this.setImages(myItemlist);
			}
			else if(this.Get_Type()==�萔.�{�^��ID_�A�C�e��06)
			{
				this.myItemlist=new BufferedImage[MAXPAGE];
				this.myItemlist[0]=owner.getImage("item/hanabi.gif");
				this.myItemlist[1]=owner.getImage("item/remiryabukuro.gif");
				this.myItemlist[2]=owner.getImage("item/black.gif");				
				this.setImages(myItemlist);				
			}
			//this.HatenaImage = owner.getImages("item/hatena.gif",1,1);
			this.HatenaImage = ImageLoader.Get_Instance(owner).getBufferedImage(�萔.�摜�ԍ�_�A�C�e���n�e�i);
			
			this.setAnimate(false);
			updateItemButton();
		}
	}
	
	@Override
	public void doOnMouseEvent()
	{
		int itemnum = PageIndex*7 + (this.Get_Type()-�萔.�{�^��ID_�A�C�e��01);	//���ݕ\�����Ă���A�C�e��no���Z�o
		if(itemnum<=�萔.�A�C�e���ő�ԍ�)											//�A�C�e���Ƃ��đ��݂���ԍ��Ȃ�
		{
			if((this.Get_Type()>=�萔.�{�^��ID_�A�C�e��01)&&(this.Get_Type()<=�萔.�{�^��ID_�A�C�e��06))
			{
				if(ItemManager.Get_Instance(owner).isKnown(itemnum))
				{
					if(Widget_Manager.Get_Instance(owner).getMessenger()!=this)
					{
						Widget_Manager.Get_Instance(owner).ClsMessage();
						Widget_Manager.Get_Instance(owner).DrawMessage(
																ItemManager.Get_Instance(owner).getItemExplanation(itemnum),
																this);
					}
				}
			}
		}
	}
	
	@Override
	public void doClickEvent()
	{
		super.doClickEvent();

		//System.out.println("����"+this.Get_Type());
		if(this.Get_Type()==�萔.�{�^��ID_����{�^��)
		{
			this.Get_Root_Parent().setActiveToFade(false);
			return;
		}

		int itemnum = PageIndex*7 + (this.Get_Type()-�萔.�{�^��ID_�A�C�e��01);	//���ݕ\�����Ă���A�C�e��no���Z�o
		if(itemnum<=�萔.�A�C�e���ő�ԍ�)											//�A�C�e���Ƃ��đ��݂���ԍ��Ȃ�
		{
			if((this.Get_Type()>=�萔.�{�^��ID_�A�C�e��01)&&(this.Get_Type()<=�萔.�{�^��ID_�A�C�e��06))
			{
				ItemManager.Get_Instance(owner).UseItem(itemnum);
			}
		}

	}
	
	public void updateItemButton()
	{
		int itemnum = PageIndex*7 + (this.Get_Type()-�萔.�{�^��ID_�A�C�e��01);	//���ݕ\�����Ă���A�C�e��no���Z�o
		if(itemnum<=�萔.�A�C�e���ő�ԍ�)											//�A�C�e���Ƃ��đ��݂���ԍ��Ȃ�
		{
			if(ItemManager.Get_Instance(owner).isKnown(itemnum))	// �m���Ă���Ε\��
			{
				this.setImages(myItemlist);
				if(this.PageIndex!=this.getFrame())
				{
					this.setFrame(this.PageIndex);
				}
			}
			else
			{
				this.setImages(this.HatenaImage);					// �m��Ȃ���΃n�e�i�\��
			}
		}
	}
	
    @Override
	public void update(long elapsedTime)
    {
    	super.update(elapsedTime);
    	if(this.Get_Parent()!=null)
    	{
    		updateItemButton();
    	}
    }

	public int getPageIndex() {
		return PageIndex;
	}

	public void setPageIndex(int pageIndex) {
		PageIndex = pageIndex;
	}
}
