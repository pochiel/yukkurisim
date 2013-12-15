package yukkurisim;

import gamestatus.SaveData;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class House_Object extends Object_base {
	private ADV_SpriteGroup_base	MyGroup;
	private House_Object MyObject[];
	public boolean isParent;
	
	public void Initialize(BufferedImage[] img)
	{
		if(img==null)
		{	// �}�X�^�[�I�u�W�F�N�g�̏���
			this.isParent=true;
			double t=0;
			this.MyGroup = new ADV_SpriteGroup_base(owner,"HOUSE_GROUP");

			if(my_y%2!=0)
			{
				t+=0.5;		// y�ʒu�ɂ�鉡�ʒu�␳�␳
			}
			
			// �������g�̉摜���쐬�A�o�^
			//this.setImages(owner.getImages("image/mapchip/home3.gif", 1, 1));
			this.setImages(ImageLoader.Get_Instance(owner).getBufferedImage(�萔.�摜�ԍ�_��3));
			//this.location((int)(my_x+t),my_y-2);
			System.out.println("this.myx->"+my_x+"   this.my_y->"+my_y);
			MyGroup.add(this);
			//MyGroup.add(new House_Object(owner,owner.getImages("image/mapchip/home1.gif", 1, 1),(int)(my_x+t+0.5),my_y+1));
			//MyGroup.add(new House_Object(owner,owner.getImages("image/mapchip/home2.gif", 1, 1),(int)(my_x+t+1),my_y+2));
			//MyGroup.add(new House_Object(owner,owner.getImages("image/mapchip/home4.gif", 1, 1),(int)(my_x+t+0.5),my_y-1));
			//MyGroup.add(new House_Object(owner,owner.getImages("image/mapchip/home5.gif", 1, 1),(int)(my_x+t+1),my_y));
			//MyGroup.add(new House_Object(owner,owner.getImages("image/mapchip/home6.gif", 1, 1),(int)(my_x+t+1.5),my_y+1));
			MyGroup.add(new House_Object(owner,ImageLoader.Get_Instance(owner).getBufferedImage(�萔.�摜�ԍ�_��1),(int)(my_x+t+0.5),my_y+1));
			MyGroup.add(new House_Object(owner,ImageLoader.Get_Instance(owner).getBufferedImage(�萔.�摜�ԍ�_��2),(int)(my_x+t+1),my_y+2));
			MyGroup.add(new House_Object(owner,ImageLoader.Get_Instance(owner).getBufferedImage(�萔.�摜�ԍ�_��4),(int)(my_x+t+0.5),my_y-1));
			MyGroup.add(new House_Object(owner,ImageLoader.Get_Instance(owner).getBufferedImage(�萔.�摜�ԍ�_��5),(int)(my_x+t+1),my_y));
			MyGroup.add(new House_Object(owner,ImageLoader.Get_Instance(owner).getBufferedImage(�萔.�摜�ԍ�_��6),(int)(my_x+t+1.5),my_y+1));
			
			this.setAnimate(false); 				// �A�j���[�V�������Ȃ�
			this.setLoopAnim(false);				// �A�j���[�V���������[�v�����Ȃ�
			this.setActiveToFade(true);
			
			this.setX(this.Get_Pixel_x(this.my_x,this.my_y)-(this.getWidth()-�萔.�}�b�v�`�b�v����));
			this.setY(this.Get_Pixel_y(this.my_x,this.my_y)-(this.getHeight()-�萔.�}�b�v�`�b�v�c��));
					

		}
		else
		{
			this.isParent = false;
			this.setImages(img);
			this.setAnimate(false); 				// �A�j���[�V�������Ȃ�
			this.setLoopAnim(false);				// �A�j���[�V���������[�v�����Ȃ�
			this.setActiveToFade(true);
			
			this.setX(this.Get_Pixel_x(this.my_x,this.my_y)-(this.getWidth()-�萔.�}�b�v�`�b�v����));
			this.setY(this.Get_Pixel_y(this.my_x,this.my_y)-(this.getHeight()-�萔.�}�b�v�`�b�v�c��));
					
			PhLaw.Get_ObjectManager().add(this, �萔.TYPE_��object);
		}
		
	}
	
	/* �q�C���[�W�I�u�W�F�N�g���쐬����R���X�g���N�^ */
	public House_Object(yukkurisim_main own, BufferedImage[] arg0, int x, int y) {
		super(own, arg0);
		location(x,y);
		Initialize(arg0);
	}

	public House_Object(yukkurisim_main own, BufferedImage[] arg0, double arg1, double arg2) {
		super(own, arg0, arg1, arg2);
		//Initialize(arg0);
	}

	public House_Object(yukkurisim_main own, BufferedImage[] arg0) {
		super(own, arg0);
		//Initialize();
	}

	/* �}�X�^�[�I�u�W�F�N�g���쐬����R���X�g���N�^ */
	public House_Object(yukkurisim_main own, int arg0, int arg1) {
		super(own);
		location(arg0,arg1);
		Initialize(null);
		System.out.println("���n�E�X�I�u�W�F�N�g�f�o�b�O");
		System.out.println("��myx��"+this.my_x);
		System.out.println("��myx��"+this.my_y);
	}

	public House_Object(yukkurisim_main own, double arg0, double arg1) {
		super(own, arg0, arg1);
		//Initialize(null);
	}

	public House_Object(yukkurisim_main own) {
		super(own);
		//Initialize();
	}

	@Override
	public int Get_Type() {
		return �萔.TYPE_��object;
	}

	@Override
	public void doClickEvent() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u

	}
	
	/* �e�I�u�W�F�N�g�������`����s�� */
	@Override
	public void render(Graphics2D g)
	{
		// �q�I�u�W�F�N�g�̕`��̓}�b�v�I�u�W�F�N�g�}�l�[�W���ɂ܂�����
		super.render(g);
	}
	
	public SaveData getSaveData()
	{
		SaveData mysave = new SaveData();
		mysave.my_x = this.my_x;
		mysave.my_y = this.my_y;
		mysave.syuzoku = this.Get_Type();
		mysave.itemflag = false;
		return mysave;
	}
	
	public void setSaveData(SaveData mysave)
	{
		this.my_x	=	mysave.my_x ;
		this.my_y	=	mysave.my_y ;
	}
	
	@Override
	public boolean isCharactor()
	{
		return false;	// ����̓L�����N�^�[�ł͂Ȃ�
	}

	@Override
	public void doOnMouseEvent() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		
	}
	
	/**
	 * ���ɐݒu���Ă���I�u�W�F�N�g�������H
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isAbleToBuild(int x,int y)
	{
		if( this.PhLaw.Get_ObjectManager().Get_obj_type(x, y)>�萔.TYPE_object_�L�����N�^�ƌ��z���ԍ��~���l )
		{
			return false;
		}
		return true;
	}
}
