package yukkurisim;

import gamestatus.SaveData;

import java.awt.image.BufferedImage;

/**
 * �_�n�I�u�W�F�N�g�B�@�����ڒn���Ȃ��Ɣ_�Ƃ�I�����邱�Ƃ��ł��Ȃ��B
 * @author �|�`�G��
 *
 */
public class Farm_Object extends Object_base {

	@Override
	public void Initialize()
	{
		this.setImages(ImageLoader.Get_Instance(owner).getBufferedImage(�萔.�摜�ԍ�_�_�n));
		
		this.setAnimate(false); 				// �A�j���[�V�������Ȃ�
		this.setLoopAnim(false);				// �A�j���[�V���������[�v�����Ȃ�
		
		this.setX(this.Get_Pixel_x(this.my_x,this.my_y)-(this.getWidth()-�萔.�}�b�v�`�b�v����));
		this.setY(this.Get_Pixel_y(this.my_x,this.my_y)-(this.getHeight()-�萔.�}�b�v�`�b�v�c��));
		
		//PhLaw.Get_ObjectManager().add(this, this.Get_Type());		// add�o�^��build�E�C���h�E���ōs��
	}

	public Farm_Object(yukkurisim_main own) {
		super(own);
		Initialize();
	}

	public Farm_Object(yukkurisim_main own, BufferedImage[] arg0) {
		super(own, arg0);
		Initialize();
	}

	public Farm_Object(yukkurisim_main own, double arg0, double arg1) {
		super(own, arg0, arg1);
		Initialize();
	}

	/** �ʏ�g�p����R���X�g���N�^
	 * @param own
	 */
	public Farm_Object(yukkurisim_main own , int x , int y) {
		super(own, x, y);
		location(x,y);		// �K��Initialize�̑O��location����B
		Initialize();
	}
	
	public Farm_Object(yukkurisim_main own, BufferedImage[] arg0, double arg1,
			double arg2) {
		super(own, arg0, arg1, arg2);
		Initialize();
	}

	@Override
	public int Get_Type() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return �萔.TYPE_�_�nobject;
	}

	@Override
	public void doClickEvent() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u

	}

	@Override
	public void doOnMouseEvent() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u

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


}
