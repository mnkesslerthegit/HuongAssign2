
public interface ListInterface<T> {

	int size();

	// Re t ur ns t he numbe r of e l eme nt s on t hi s l i st .

	void add(T element);

	// Adds e l eme nt t o t hi s l i st .

	boolean contains(T element);

	// Re t ur ns t r ue i f t hi s l i st c ont a i ns a n e l eme nt e s uc h
	// t ha t

	// e . e qua l s( e l eme nt ) ; ot he r wi s e , r e t ur ns f a l s e .

	boolean remove(T element);

	// Remove s a n e l eme nt e f r om t hi s l i st s uc h t ha t e . e qua l
	// s( e l eme nt )

	// a nd r e t ur ns t r ue ; i f no s uc h e l eme nt e xi st s, r e t ur ns
	// f a l s e .

	T get(T element);

	// Returns an element e from this list such that e.equa
	// ls( element ) ;

	// i f no such element exist s, returns null .

	String toString();

	// Re t ur ns a ni c e l y f or ma t t e d st r i ng t ha t r e pr e s e nt
	// s t hi s l i st .

	void reset();

	// I ni t i a l i z e s c ur r e nt posi t i on f or a n i t e r a t i on t
	// hr ough t hi s l i st ,

	// t o t he f i r st e l eme nt on t hi s l i st .

	T getNext();

	// Pr e c ondi t i ons: The l i st i s not empt y

	// The l i st ha s be e n r e s e t

	// The l i st ha s not be e n modi f i e d si nc e most r e c e nt r e s e t

	// Re t ur ns t he e l eme nt a t t he c ur r e nt posi t i on on t hi s l i
	// st .

	// I f t he c ur r e nt posi t i on i s t he l a st e l eme nt , t he n i t
	// adva nc e s t he va l ue

	// of t he c ur r e nt posi t i on t o t he f i r st e l eme nt ; ot he r wi
	// s e , i t a dva nc e s

	// t he va l ue of t he c ur r e nt posi t i on t o t he ne xt e l eme nt .

}
