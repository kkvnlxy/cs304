package management;

public class Triple <S, L, D>
{
	final private S cat_name;
	final private L list;
	final private D num;
	
	protected Triple(S category_name, L item_lst, D total_sale)
	{
		this.cat_name = category_name;
		this.list = item_lst;
		this.num = total_sale;
	}
	
	final protected S getS()
	{
		return this.cat_name;
	}
	final protected L getL()
	{
		return this.list;
	}
	final protected D getD()
	{
		return this.num;
	}
}