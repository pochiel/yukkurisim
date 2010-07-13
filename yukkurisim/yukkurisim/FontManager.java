package yukkurisim;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;

public class FontManager extends Object_base {
	private Font yukkurifont;
	private Color	fontColor;		// フォントカラー

    public FontManager(yukkurisim_main own,int size) {
    	//コンストラクたん
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
     * ファイルからFontオブジェクトを生成
     * 
     * @param filename フォントファイル名
     * @return Fontオブジェクト
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
    
    /* fontのインスタンスを返す。　あんまり多用すべきじゃないよね。 */
    public Font Get_Font_Instance()
    {
    	return yukkurifont;
    }
    
	public void drawString(Graphics2D g,String message,int x , int y)
	{
		my_x = x;
		my_y = y;
		
		//Rectangle2D rectangle = yukkurifont.getStringBounds("あcsあ12", g.getFontRenderContext());
		int h = (int)this.getHeight(message); //縦方向のピクセル数

		g.setColor(this.fontColor);

		g.setFont(yukkurifont);
	    g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
	            RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	    g.drawString(message, my_x, my_y+h); //指定y位置の上に描画するため補正が必要
	}
	
	public int getWidth(String message)
	{
		BufferedImage img = new BufferedImage(10, 10, BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D g = img.createGraphics();

		Rectangle2D rectangle = yukkurifont.getStringBounds(message, g.getFontRenderContext());
		return (int)rectangle.getWidth(); //横方向のピクセル
	}
	
	public int getHeight(String message)
	{
		BufferedImage img = new BufferedImage(10, 10, BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D g = img.createGraphics();

		Rectangle2D rectangle = yukkurifont.getStringBounds(message, g.getFontRenderContext());
		return (int)rectangle.getHeight(); //横方向のピクセル
	}

	public int Get_Type() {
		// 不要ファンクション
		return 定数.TYPE_未使用;
	}

	public void doClickEvent() {
		// TODO 自動生成されたメソッド・スタブ
		
	}
	
	public boolean isCharactor()
	{
		return false;	// これはキャラクターではない
	}

	@Override
	public void doOnMouseEvent() {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	// オブジェクト単位で色が指定できるべきなので、Colorの退避・適用はWidgetbaseでやる
	public Color getFontColor() {
		return fontColor;
	}

	public void setFontColor(Color fontColor) {
		this.fontColor = fontColor;
	}
}
