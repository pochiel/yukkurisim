package yukkurisim;

import java.awt.image.BufferedImage;

import subwindow.Widget_Build;

/**
 * �`�F�b�N�{�b�N�X�N���X
 * @author pochiel
 *
 */
public class Widget_Checkbox extends Widget_Base {
	private boolean myValue;
	private BufferedImage[] TrueImage;
	private BufferedImage[] FalseImage;

	/**
	 * �`�F�b�N�{�b�N�X�̌��݂̒l�𓾂�
	 * @return
	 */
	public boolean Get_Value()
	{
		return myValue;
	}
	
	/**
	 * �`�F�b�N�{�b�N�X�ɒl���Z�b�g����
	 * @param t
	 */
	public void Set_Value(boolean t)
	{
		myValue = t;
	}
	
	/**
	 * �`�F�b�N�{�b�N�X�̒l�𔽓]����
	 *
	 */
	public void Change_Value()
	{
		myValue = !myValue;
	}
	
	public Widget_Checkbox(yukkurisim_main own) {
		super(own);
		Initialize();
	}

	
	public Widget_Checkbox(yukkurisim_main own,Widget_Base parent)
	{
		super(own, (BufferedImage[])null, 0, 0, parent, 0);
		Initialize();
	}
	public Widget_Checkbox(yukkurisim_main own, BufferedImage[] arg0) {
		super(own, arg0);
		Initialize();
	}

	public Widget_Checkbox(yukkurisim_main own, double arg0, double arg1) {
		super(own, arg0, arg1);
		Initialize();
	}

	public Widget_Checkbox(yukkurisim_main own, BufferedImage[] arg0, double x,
			double y) {
		super(own, arg0, x, y);
		Initialize();
	}

	public Widget_Checkbox(yukkurisim_main own, BufferedImage[] arg0, double x,
			double y, Widget_Base parent) {
		super(own, arg0, x, y, parent);
		Initialize();
	}

	public Widget_Checkbox(yukkurisim_main own, String message, double x,
			double y, Widget_Base parent) {
		super(own, message, x, y, parent);
		Initialize();
	}

	public Widget_Checkbox(yukkurisim_main own, BufferedImage[] arg0, int x,
			int y, Widget_Build parent, int mytype) {
		super(own, arg0, x, y, parent, mytype);
		Initialize();
	}

	public Widget_Checkbox(yukkurisim_main own, String message, double x,
			double y, Widget_Base parent, int mytype) {
		super(own, message, x, y, parent, mytype);
		Initialize();
	}
	@Override
	public void update(long elapsedTime){
		super.update(elapsedTime);
		this.CheckImageInstance();	// �摜�������Ă��邩�̃`�F�b�N
	}
	
	/**
	 * �`�F�b�N�{�b�N�X�̒l�ɉ摜���Ή����Ă��邩�ǂ�������
	 * �Ή����Ă��Ȃ���ΑΉ������摜�ɍ����ւ��܂��B
	 */
	public void CheckImageInstance()
	{
		if(this.getImages()==null)
		{
			if(this.Get_Value())
			{
				this.setImages(TrueImage);
			}
			else
			{
				this.setImages(FalseImage);
			}
		}
		else
		{
			if((this.Get_Value())&&(this.getImages()!=TrueImage))
			{
				this.setImages(TrueImage);
			}
			else if(!(this.Get_Value())&&(this.getImages()!=FalseImage))
			{
				this.setImages(FalseImage);
			}
		}
	}
	
	@Override
	public void Initialize()
	{
		//this.TrueImage = owner.getImages("image/widget/checkT.GIF", 1, 1);
		//this.FalseImage = owner.getImages("image/widget/checkF.GIF", 1, 1);
		this.TrueImage = ImageLoader.Get_Instance(owner).getBufferedImage(�萔.�摜�ԍ�_�`�F�b�N�{�b�N�X�I��);
		this.FalseImage = ImageLoader.Get_Instance(owner).getBufferedImage(�萔.�摜�ԍ�_�`�F�b�N�{�b�N�X�I�t);
		this.Set_Value(false);
		this.CheckImageInstance();
	}
	
	/***
	 * �N���b�N���̃C�x���g�L�q
	 */
	@Override
	public void doClickEvent()
	{
		this.Change_Value();
	}
}
