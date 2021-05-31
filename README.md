# Using a RecyclerView

Enlaces relacionados:

[https://developer.android.com/guide/topics/ui/layout/recyclerview](https://developer.android.com/guide/topics/ui/layout/recyclerview)

[https://developer.android.com/guide/topics/ui/layout/recyclerview-custom](https://developer.android.com/guide/topics/ui/layout/recyclerview-custom)

[https://antonioleiva.com/recyclerview-listener/](https://antonioleiva.com/recyclerview-listener/)

[https://github.com/android/views-widgets-samples/tree/main/RecyclerView](https://github.com/android/views-widgets-samples/tree/main/RecyclerView)

Para mostrar un conjunto de datos en un RecyclerView se necesitan dos clases: el adaptador y el *viewholder*.

Primero se ha de crear el *viewholder* que extiende **RecyclerView.ViewHolder**. En el *viewholder* se obtiene acceso a todos los elementos
*view* que componen un elemento visualizado de la lista de datos.

Después se crea el adaptador que extiende **RecyclerView.Adapter<ViewHolder>**. Se han de implementar estos tres métodos:

- public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType)\
Este método se ejecuta cuando es necesario crear un elemento de lista.

- public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position)\
Este método se ejecuta cuando se va a visualizar un elemento de la lista.

- public int getItemCount()\
Este método devuelve el número de elementos que contiene la lista de datos.

## Implementar un RecyclerView Listener

El RecyclerView no implementa un listener predeterminado, por lo que se tendrá que implementar. Hay mucha formas de implementar un listener.

Se puede implementar una *interface* y posteriornmente asignar un elemento del tipo de la *interface* en el método *onBindViewHolder()*.

```java
public interface OnItemClickListener {
    void onItemClick(View view);
}
```

```java
private OnItemClickListener listener;

public Adapter(OnItemClickListener listener) {
    this.listener = listener;
}

@Override
public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
    //...
    holder.getItem().setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            listener.onItemClick(v);
            //también se puede hacer referencia a position y holder
        }
    });
}
```

```java
OnItemClickListener listener = new OnItemClickListener() {
    @Override
    public void onItemClick(View view) {
        //...
    }
};

Adapter adapter = new Adapter(listener);
```
