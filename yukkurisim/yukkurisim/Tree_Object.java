/**
 * 
 */
package yukkurisim;

import gamestatus.SaveData;

import java.awt.image.BufferedImage;
/**
 * @author �|�`�G��
 *
 */
public class Tree_Object extends Object_base {

	@Override
	public void Initialize()
	{
		//this.setImages(owner.getImages("image/wood1.gif",1,1));
		this.setImages(ImageLoader.Get_Instance(owner).getBufferedImage(�萔.�摜�ԍ�_��));
		
		this.setAnimate(false); 				// �A�j���[�V�������Ȃ�
		this.setLoopAnim(false);				// �A�j���[�V���������[�v�����Ȃ�
		
		this.setX(this.Get_Pixel_x(this.my_x,this.my_y)-(this.getWidth()-�萔.�}�b�v�`�b�v����));
		this.setY(this.Get_Pixel_y(this.my_x,this.my_y)-(this.getHeight()-�萔.�}�b�v�`�b�v�c��));
		
		this.setActiveToFade(true);
				
		//PhLaw.Get_ObjectManager().add(this, �萔.TYPE_��object);	// add�o�^��build�E�C���h�E���ōs��

	}
	/**
	 * @param own
	 */
	public Tree_Object(yukkurisim_main own) {
		super(own);
		// TODO �����������ꂽ�R���X�g���N�^�[�E�X�^�u
	}

	/** �ʏ�g�p����R���X�g���N�^
	 * @param own
	 */
	public Tree_Object(yukkurisim_main own , int x , int y) {
		super(own);
		location(x,y);// �K��Initialize�̑O��location����B
		Initialize();
	}

	/**
	 * @param own
	 * @param arg0
	 */
	public Tree_Object(yukkurisim_main own, BufferedImage[] arg0) {
		super(own, arg0);
		// TODO �����������ꂽ�R���X�g���N�^�[�E�X�^�u
	}

	/**
	 * @param own
	 * @param arg0
	 * @param arg1
	 */
	public Tree_Object(yukkurisim_main own, double arg0, double arg1) {
		super(own, arg0, arg1);
		// TODO �����������ꂽ�R���X�g���N�^�[�E�X�^�u
	}

	/**
	 * @param own
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 */
	public Tree_Object(yukkurisim_main own, BufferedImage[] arg0, double arg1,
			double arg2) {
		super(own, arg0, arg1, arg2);
		// TODO �����������ꂽ�R���X�g���N�^�[�E�X�^�u
	}
	
	public int Get_Food()
	{
		return �萔.����̒���*3;
	}
	
	@Override
	public void doClickEvent()
	{
		//�������Ȃ�
	}
	@Override
	public int Get_Type() {
		return �萔.TYPE_��object;
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
