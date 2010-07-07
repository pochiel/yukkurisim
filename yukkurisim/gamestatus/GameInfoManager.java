package gamestatus;

public class GameInfoManager {
	protected  Const_Value 定数 = new Const_Value();
	private static GameInfoManager myself;
	private int myCash;
	private int call_day;
	
	/******* インスタンスを得る ********/
	public static synchronized GameInfoManager Get_Instance( )
	{
		if( myself == null)
		{
			myself = new GameInfoManager();
		}

		return myself;
	}
	
	public GameInfoManager()
	{
		myCash = 定数.所持金初期値;
		PlayPartState = new int[定数.TYPE_キャラクター系タイプ総数];
		for(int i=0;i<定数.TYPE_キャラクター系タイプ総数;i++)
		{
			PlayPartState[i]=0;
		}
	}
	
	/**
	 * 所持金を返す
	 * @return
	 */
	public int GetCash()
	{
		return myCash;
	}
	
	/*
	 * 所持金をセットする
	 */
	public void SetCash(int c)
	{
		myCash = c;
	}

	/**
	 * オブジェクト設置に必要な金額を返す。
	 * @return
	 */
	public int getCost(int objtype)
	{
		if(objtype==定数.TYPE_木object)
		{
			return 200;
		}
		else if(objtype==定数.TYPE_家object)
		{
			return 400;
		}
		else if(objtype==定数.TYPE_農地object)
		{
			return 300;
		}
		return 0;
	}
	
	private int[] PlayPartState;

	/**
	 * 種族ごとの行動指定結果のgetter
	 * @return
	 */
	public int getPlayPartState(int i) {
		return PlayPartState[i];
	}
	
	/**
	 * 種族ごとの行動指定結果のsetter
	 * @param playPartState
	 */
	public void setPlayPartState(int syuzoku,int playPartState) {
		PlayPartState[syuzoku] = playPartState;
	}
}
