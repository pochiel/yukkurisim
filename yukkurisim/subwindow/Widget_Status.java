package subwindow;

import java.awt.image.BufferedImage;
import com.golden.gamedev.object.Sprite;

import yukkurisim.Widget_Base;
import yukkurisim.Widget_Manager;
import yukkurisim.yukkurisim_main;
import yukkurisim.ImageLoader;
public class Widget_Status extends Widget_Base {
	private boolean Targetting;
	/** ���̑��摜�f�[�^�\���p�摜�f�[�^�Q�� **/
	private BufferedImage[] reimu_image = null;		// �ꂢ�ނ̃C���[�W
	private BufferedImage[] marisa_image = null;		// �܂肳�̃C���[�W

	public Widget_Status(yukkurisim_main own) {
		super(own);
		Initialize();
	}

	public Widget_Status(yukkurisim_main own, BufferedImage[] arg0) {
		super(own, arg0);
		Initialize();
	}

	public Widget_Status(yukkurisim_main own, double arg0, double arg1) {
		super(own, arg0, arg1);
		Initialize();
	}

	public Widget_Status(yukkurisim_main own, BufferedImage[] arg0, double x,
			double y) {
		super(own, arg0, x, y);
		Initialize();
	}

	public Widget_Status(yukkurisim_main own, BufferedImage[] arg0, double x,
			double y, Widget_Base parent) {
		super(own, arg0, x, y, parent);
		Initialize();
	}

	public Widget_Status(yukkurisim_main own, String message, double x,
			double y, Widget_Base parent) {
		super(own, message, x, y, parent);
		Initialize();
	}

	public Widget_Status(yukkurisim_main own, BufferedImage[] arg0, int x,
			int y, Widget_Base parent, int mytype) {
		super(own, arg0, x, y, parent, mytype);
		Initialize();
	}

	public Widget_Status(yukkurisim_main own, String message, double x,
			double y, Widget_Base parent, int mytype) {
		super(own, message, x, y, parent, mytype);
		this.SetFontsize(11);
		Initialize();
	}
	
	@Override
	public void doClickEvent()
	{
		super.doClickEvent();

		if(Button_id==�萔.�{�^��ID_����{�^��)
		{
			this.Get_Root_Parent().setActiveToFade(false);
			Widget_Manager.Get_Instance(owner).GTimer.Start_Timer();
		}
	}

	public boolean isTargetting() {
		return Targetting;
	}

	public void setTargetting(boolean targetting) {
		Targetting = targetting;
	}
	
	@Override
	public void Initialize()
	{
		super.Initialize();
		// ���̑��摜�\���p�摜�f�[�^����������
		if(Button_id==�萔.���̑�ID_�摜�\��)
		{
			//this.reimu_image = owner.getImages("image/reimuwait.gif", 1, 6);
			this.reimu_image = ImageLoader.Get_Instance(owner).getBufferedImage(�萔.�摜�ԍ�_�������ꂢ�ޑ҂�);
			this.setImages(reimu_image);
			
			this.setAnimate(true);				// �A�j���[�V����������
			this.setLoopAnim(true);			// ���[�v������
		}
	}

	/*****************�����܂��\����������擾�n�֐���������*****************/
	/*****************���������I �����܂��\������������X�����������邱��**/
	public String getSyuzokuString( int syuzoku )
	{		
		if(syuzoku==�萔.TYPE_�������object)
		{
			return "�ꂢ��";
		}
		else if(syuzoku==�萔.TYPE_�܂肳object)
		{
			return "�܂肳";
		}
		return "NULL";
	}
	
	public String getTairyokuString( int tairyoku )
	{
		String[] serifu={	"�m���̂�������Ȃ�",
				"�ǂ����Ă��Q�X",
				"���҂����畉��",
				"���肬�蓮��",
				"�����ʈȉ�",
				"������肵�Ă�ˁI",
				"���΂ł���",
				"����������",
				"��������Ƒ��X�y�V����",
				"���Ȃ��Ƃ�������������"};

		for(int i=0;i<10;i++)
		{
			if(	(tairyoku>=(�萔.C_�X�e�[�^�X�ő�l*i/10))&&
				(tairyoku<(�萔.C_�X�e�[�^�X�ő�l*(i+1)/10)))
			{
				return serifu[i];
			}
		}
		if(tairyoku==�萔.C_�X�e�[�^�X�ő�l)
		{
			return "�݂�Ȃ�����肵�Ă����ĂˁI";
		}
		return "null";
		
	}
	
	public String getKokoroString( int kokoro )
	{
		String[] serifu={	"�m���̂�������Ȃ�",
				"�ǂ����Ă��Q�X",
				"���҂����畉��",
				"���肬�蓮��",
				"�����ʈȉ�",
				"������肵�Ă�ˁI",
				"���΂ł���",
				"����������",
				"��������Ƒ��X�y�V����",
				"���Ȃ��Ƃ�������������"};

		for(int i=0;i<10;i++)
		{
			if(	(kokoro>=(�萔.C_�X�e�[�^�X�ő�l*i/10))&&
				(kokoro<(�萔.C_�X�e�[�^�X�ő�l*(i+1)/10)))
			{
				return serifu[i];
			}
		}
		if(kokoro==�萔.C_�X�e�[�^�X�ő�l)
		{
			return "�݂�Ȃ�����肵�Ă����ĂˁI";
		}
		return "null";
		
	}
	
	public String getChikaraString( int chikara )
	{
		String[] serifu={	"�m���̂�������Ȃ�",
				"�ǂ����Ă��Q�X",
				"���҂����畉��",
				"���肬�蓮��",
				"�����ʈȉ�",
				"������肵�Ă�ˁI",
				"���΂ł���",
				"����������",
				"��������Ƒ��X�y�V����",
				"���Ȃ��Ƃ�������������"};

		for(int i=0;i<10;i++)
		{
			if(	(chikara>=(�萔.C_�X�e�[�^�X�ő�l*i/10))&&
				(chikara<(�萔.C_�X�e�[�^�X�ő�l*(i+1)/10)))
			{
				return serifu[i];
			}
		}
		if(chikara==�萔.C_�X�e�[�^�X�ő�l)
		{
			return "�݂�Ȃ�����肵�Ă����ĂˁI";
		}
		return "null";
		
	}
	
	public String getAmasaString( int amasa )
	{
		String[] serifu={	"�m���̂�������Ȃ�",
				"�ǂ����Ă��Q�X",
				"���҂����畉��",
				"���肬�蓮��",
				"�����ʈȉ�",
				"������肵�Ă�ˁI",
				"���΂ł���",
				"����������",
				"��������Ƒ��X�y�V����",
				"���Ȃ��Ƃ�������������"};

		for(int i=0;i<10;i++)
		{
			if(	(amasa>=(�萔.C_�X�e�[�^�X�ő�l*i/10))&&
				(amasa<(�萔.C_�X�e�[�^�X�ő�l*(i+1)/10)))
			{
				return serifu[i];
			}
		}
		if(amasa==�萔.C_�X�e�[�^�X�ő�l)
		{
			return "�݂�Ȃ�����肵�Ă����ĂˁI";
		}
		return "null";		
		
	}
	
	public String getOnakaString( int onaka )
	{
		String[] serifu={	"�m���̂�������Ȃ�",
				"�ǂ����Ă��Q�X",
				"���҂����畉��",
				"���肬�蓮��",
				"�����ʈȉ�",
				"������肵�Ă�ˁI",
				"���΂ł���",
				"����������",
				"��������Ƒ��X�y�V����",
				"���Ȃ��Ƃ�������������"};

		for(int i=0;i<10;i++)
		{
			if(	(onaka>=(�萔.MAX_HUNGRY_VALUE*i/10))&&
				(onaka<(�萔.MAX_HUNGRY_VALUE*(i+1)/10)))
			{
				return serifu[i];
			}
		}
		if(onaka==�萔.C_�X�e�[�^�X�ő�l)
		{
			return "�݂�Ȃ�����肵�Ă����ĂˁI";
		}
		return "null";		
		
	}
	/*****************�����܂��\����������擾�n�֐������܂�*****************/
	
	/**
	 * �X�e�[�^�X�E�C���h�E�ɒl��\�����܂��B
	 * @param syuzoku
	 * @param tairyoku
	 * @param kokoro
	 * @param chikara
	 * @param amasa
	 * @param onaka
	 */
	public void showStatusWidget(	int syuzoku ,
									int tairyoku ,
									int kokoro ,
									int chikara ,
									int amasa ,
									int onaka ,
									int days)
	{
		if(this.Get_Parent()==null )
		{
			this.setActiveToFade(true);
			Sprite[] child = this.getChild().getSprites();
			Widget_Status tmp;
			for(int i=0;i<this.Get_Child_Size();i++)
			{
				tmp = (Widget_Status)child[i];
				if( tmp.Get_Type()==�萔.���x��ID_�X�e�[�^�X_�푰 )
				{
					tmp.Set_Mymessage(this.getSyuzokuString(syuzoku));
				}
				if( (tmp.isActive())&&(tmp.Get_Type()==�萔.���x��ID_�X�e�[�^�X_�̗�) )
				{
					tmp.Set_Mymessage(this.getTairyokuString(tairyoku));
				}
				if( (tmp.isActive())&&(tmp.Get_Type()==�萔.���x��ID_�X�e�[�^�X_������) )
				{
					tmp.Set_Mymessage(this.getTairyokuString(tairyoku));
				}
				if( (tmp.isActive())&&(tmp.Get_Type()==�萔.���x��ID_�X�e�[�^�X_������) )
				{
					tmp.Set_Mymessage(this.getChikaraString(chikara));
				}
				if( (tmp.isActive())&&(tmp.Get_Type()==�萔.���x��ID_�X�e�[�^�X_�Â�) )
				{
					tmp.Set_Mymessage(this.getAmasaString(amasa));
				}
				if( (tmp.isActive())&&(tmp.Get_Type()==�萔.���x��ID_�X�e�[�^�X_���Ȃ�) )
				{
					tmp.Set_Mymessage(this.getOnakaString(onaka));
				}
				if( (tmp.isActive())&&(tmp.Get_Type()==�萔.���̑�ID_�摜�\��) )
				{
					if(syuzoku==�萔.TYPE_�������object)
					{
						tmp.setImages(tmp.reimu_image);
					}
					else if(syuzoku==�萔.TYPE_�܂肳object)
					{
						//�摜�����ւ��������͂���
					}

				}
				if( (tmp.isActive())&&(tmp.Get_Type()==�萔.���x��ID_�X�e�[�^�X_����) )
				{
					tmp.Set_Mymessage("���� "+(Widget_Manager.Get_Instance(owner).GTimer.Get_Days()-days)+"����");
				}
				
			}
		}
	}
}
