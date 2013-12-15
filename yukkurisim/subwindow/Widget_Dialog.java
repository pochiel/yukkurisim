package subwindow;

import gamestatus.GameTimer;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

import yukkurisim.ADV_SpriteGroup_base;
import yukkurisim.Object_base;
import yukkurisim.Widget_Base;
import yukkurisim.yukkurisim_main;
import yukkurisim.ImageLoader;

public class Widget_Dialog extends Widget_Base {
	private Widget_Dialog Widget_OK;			// OK�{�^��
	private ADV_SpriteGroup_base OK_But;		// OK�{�^��
	private Widget_Base Label_Popup;			// ���x��
	Queue<String> PopupQue;
	
	public Widget_Dialog(yukkurisim_main own) {
		super(own);
		Initialize();
	}

	public Widget_Dialog(yukkurisim_main own, BufferedImage[] arg0) {
		super(own, arg0);
		Initialize();
	}

	public Widget_Dialog(yukkurisim_main own, double arg0, double arg1) {
		super(own, arg0, arg1);
		Initialize();
	}

	public Widget_Dialog(yukkurisim_main own, BufferedImage[] arg0, double x,
			double y) {
		super(own, arg0, x, y);
		Initialize();
	}

	public Widget_Dialog(yukkurisim_main own, BufferedImage[] arg0, double x,
			double y, Widget_Base parent) {
		super(own, arg0, x, y, parent);
		Initialize();
	}

	public Widget_Dialog(yukkurisim_main own, String message, double x,
			double y, Widget_Base parent) {
		super(own, message, x, y, parent);
		Initialize();
	}

	public Widget_Dialog(yukkurisim_main own, BufferedImage[] arg0, int x,
			int y, Widget_Base parent, int mytype) {
		super(own, arg0, x, y, parent, mytype);
		Initialize();
	}

	public Widget_Dialog(yukkurisim_main own, String message, double x,
			double y, Widget_Base parent, int mytype) {
		super(own, message, x, y, parent, mytype);
		Initialize();
	}
	
	@Override
	public void doClickEvent()
	{
		super.doClickEvent();
		if(Button_id==�萔.�{�^��ID_�_�C�A���OOK)
		{
			Widget_Dialog tmp = (Widget_Dialog)this.Get_Root_Parent();
			tmp.Kill_Dialog_Window();
		}
	}

	@Override
	public void Initialize()
	{
		if(this.Get_Parent()==null)
		{
			PopupQue = new LinkedList<String>();
			
			Label_Popup = new Widget_Dialog(owner,"",0,0,this,�萔.�{�^��ID_�k��);
			this.Mount_widget(Label_Popup, 0, 0);
			this.Widget_OK = new Widget_Dialog(	owner,
													//owner.getImages("image/widget/DialogOK.GIF",1,1),
													ImageLoader.Get_Instance(owner).getBufferedImage(�萔.�摜�ԍ�_�_�C�A���OOK),
													0,0,
													this,
													�萔.�{�^��ID_�_�C�A���OOK);
			this.Mount_widget(Widget_OK, 135, 160);
			this.OK_But = new ADV_SpriteGroup_base(owner,"DIALOG_OK_BUTTON");
			OK_But.add(Widget_OK);
			OK_But.add(Label_Popup);
			
	       	/************* Comparator �Z�b�g **************/
	    	this.OK_But.setComparator(
	    			new Comparator(){
	    				public int compare(Object o1, Object o2){
	    					Object_base	s1 = (Object_base) o1,
	    								s2 = (Object_base) o2;
	    					return (int)(s1.Get_Id() - s2.Get_Id() );
	    				}
	    			}
	    	);
	    	
	    	/************* ���̃O���[�v�͔�A�N�e�B�u�ȃI�u�W�F�N�g���폜���Ȃ� **********/
	    	this.OK_But.getScanFrequence().setActive(false);

		}
	}
	@Override
	public void update(long elapsedTime)
	{
		super.update(elapsedTime);
		if(this.Get_Parent()==null)
		{
			OK_But.update(elapsedTime);
			OK_But.doClickEvent();
		}
	}
	
    @Override
	public void render(Graphics2D g)
    {
    	super.render(g);
    	if(this.Get_Parent()==null)
    	{
    		OK_But.render(g);
    	}
    }
    
	public void Popup_Dialog_Window(String message)
	{
		if( PopupQue.size()<1 )
		{
			this.setActiveToFade(true);
			PopupQue.offer(message);
			//this.BackupTimerStatus=GTimer.isMoving();	�K�v�ɂȂ邾�낤���H
			GameTimer.Get_Instance(owner, �萔.�摜�ԍ�_�^�C�}).Stop_Timer();
			this.Label_Popup.Set_Mymessage(PopupQue.peek());	// ���o�����邪�A�폜���Ȃ�
			this.Label_Popup.Set_Relative_Position((this.getWidth()/2)-(Label_Popup.getWidth()/2), 40);
		}
		else
		{
			PopupQue.offer(message);	//�ǉ�
		}
	}
	
	public void Kill_Dialog_Window()
	{
		if( PopupQue.size()>1 )		// 2,3,4... �̏ꍇ
		{	// �܂����b�Z�[�W�c���Ă܂����
			PopupQue.remove();	//�Ő擪�i���ݕ\�����Ă��郁�b�Z�[�W�j���폜
			this.Label_Popup.Set_Mymessage(PopupQue.peek());
			this.Label_Popup.Set_Relative_Position((this.getWidth()/2)-(Label_Popup.getWidth()/2), 40);
		}
		else						// 1,0 �̏ꍇ
		{
			System.out.println("���L���[�T�C�Y��"+PopupQue.size());
			PopupQue.remove();	//�Ő擪�i���ݕ\�����Ă��郁�b�Z�[�W�j���폜
			this.setActiveToFade(false);
			Label_Popup.setActiveToFade(false);
			GameTimer.Get_Instance(owner, �萔.�摜�ԍ�_�^�C�}).Set_Timer_State(true);
		}
	}

	
}
