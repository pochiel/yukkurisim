package gamestatus;

public class SyuzokuInfoManager{
	protected  Const_Value 定数 = new Const_Value();
	
	public String Get_SyuzokuName(int SyuzokuNo)
	{
		if(SyuzokuNo==定数.TYPE_ゆっくりobject)
		{
			return "れいむ";
		}
		else if(SyuzokuNo==定数.TYPE_まりさobject)
		{
			return "まりさ";
		}
		return "NULL";
	}
	
	/** 狩の能力を文字化するメソッド。
	 */
	public String Get_rank_kari(int input)
	{
		String[] serifu={	"正にまんじゅう",
							"ふけばとぶよな",
							"やるきがあるのか",
							"じそく１キロ",
							"こいぬ以下",
							"ゆっくりしてるね！",
							"こいつうごくぞ",
							"ちからこそぱわー",
							"いわをもうごかす",
							"でんせつになってる"};

		for(int i=0;i<10;i++)
		{
			if(	(input>=(定数.C_ステータス最大値*i/10))&&
				(input<(定数.C_ステータス最大値*(i+1)/10)))
			{
				return serifu[i];
			}
		}
		if(input==定数.C_ステータス最大値)
		{
			return "マッハで有頂天";
		}
		return null;
	}
	
	/**協調性を文字化するメソッド。
	 */
	public String Get_rank_Cooperative(int input)
	{
		String[] serifu={	"知性のかけらもない",
				"どう見てもゲス",
				"期待したら負け",
				"ぎりぎり動物",
				"こいぬ以下",
				"ゆっくりしてるね！",
				"やればできる",
				"愛を感じる",
				"ゆっくり大家族スペシャル",
				"あなたとがったいしたい"};

		for(int i=0;i<10;i++)
		{
		if(	(input>=(定数.C_ステータス最大値*i/10))&&
			(input<(定数.C_ステータス最大値*(i+1)/10)))
		{
			return serifu[i];
		}
		}
		if(input==定数.C_ステータス最大値)
		{
		return "みんなゆっくりしていってね！";
		}
		return null;

	}
}