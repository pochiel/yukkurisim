package yukkurisim;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;

public class FontManager extends Object_base {
	private Font yukkurifont;
	private Color	fontColor;		// �t�H���g�J���[

    public FontManager(yukkurisim_main own,int size) {
    	//�R���X�g���N����
    	super(own);
		yukkurifont = createFont("/Resource/mikiyu-penji-p.ttf");
		SetFontSize(size);
		this.fontColor = Color.black;
	}

    public void SetFontSize(int size)
    {
		yukkurifont = yukkurifont.deriveFont((float)size);
    }
	/**
     * �t�@�C������Font�I�u�W�F�N�g�𐶐�
     * 
     * @param filename �t�H���g�t�@�C����
     * @return Font�I�u�W�F�N�g
     * code from http://javagame.skr.jp/index.php?%A5%D5%A5%A9%A5%F3%A5%C8%A5%D5%A5%A1%A5%A4%A5%EB%A4%AB%A4%E9%A5%D5%A5%A9%A5%F3%A5%C8%A4%F2%C6%C9%A4%DF%B9%FE%A4%E0
     */
    private Font createFont(String filename) {
        Font font = null;
        InputStream is = null;

        try {
            is = getClass().getResourceAsStream(filename);
            font = Font.createFont(Font.TRUETYPE_FONT, is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        }

        return font;
    }
    
    /* font�̃C���X�^���X��Ԃ��B�@����܂葽�p���ׂ�����Ȃ���ˁB */
    public Font Get_Font_Instance()
    {
    	return yukkurifont;
    }
    
	public void drawString(Graphics2D g,String message,int x , int y)
	{
		my_x = x;
		my_y = y;
		
		//Rectangle2D rectangle = yukkurifont.getStringBounds("��cs��12", g.getFontRenderContext());
		int h = this.getHeight(message); //�c�����̃s�N�Z����

		g.setColor(this.fontColor);

		g.setFont(yukkurifont);
	    g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
	            RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	    g.drawString(message, my_x, my_y+h); //�w��y�ʒu�̏�ɕ`�悷�邽�ߕ␳���K�v
	}
	
	public int getWidth(String message)
	{
		BufferedImage img = new BufferedImage(10, 10, BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D g = img.createGraphics();

		Rectangle2D rectangle = yukkurifont.getStringBounds(message, g.getFontRenderContext());
		return (int)rectangle.getWidth(); //�������̃s�N�Z��
	}
	
	public int getHeight(String message)
	{
		BufferedImage img = new BufferedImage(10, 10, BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D g = img.createGraphics();

		Rectangle2D rectangle = yukkurifont.getStringBounds(message, g.getFontRenderContext());
		return (int)rectangle.getHeight(); //�������̃s�N�Z��
	}

	@Override
	public int Get_Type() {
		// �s�v�t�@���N�V����
		return �萔.TYPE_���g�p;
	}

	@Override
	public void doClickEvent() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		
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

	// �I�u�W�F�N�g�P�ʂŐF���w��ł���ׂ��Ȃ̂ŁAColor�̑ޔ��E�K�p��Widgetbase�ł��
	public Color getFontColor() {
		return fontColor;
	}

	public void setFontColor(Color fontColor) {
		this.fontColor = fontColor;
	}
}
