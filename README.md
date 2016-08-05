# MyZhihu
MVP+Retrofit+RxJava

## 网络
使用了 Retrofit + RxJava 进行异步网络请求  

1.创建接口  
~~~ java
public interface ApiService {
    @GET("latest")
    Observable<Zhihu> getZhihuResponse();

    @GET("{id}")
    Observable<Story> getStory(@Path("id") String id);
}
~~~
2.获取 Retrofit 实例  
~~~ java 
Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
~~~
3.网络操作
~~~ java
retrofit.create(ApiService.class)
        .getZhihuResponse()
        .subscribeOn(Schedulers.io()) // 网络操作在子线程
        .observeOn(AndroidSchedulers.mainThread()) // 在主线程更新UI
        .subscribe(new Subscriber<Zhihu>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Zhihu zhihu) {
                // do something with zhihu
            }
        });
~~~
参考文章:  
*[Retrofit](http://square.github.io/retrofit/)  
*[RxJava](http://gank.io/post/560e15be2dca930e00da1083)  
*[Retrofit + RxJava](http://gank.io/post/56e80c2c677659311bed9841)  

## 给 RecyclerView 加一个 Header
~~~ java
public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    
    private List<Item> items = new ArrayList<>();
    
    public Adapter(List<Item> items) {
        this.items = items;
    }
    
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.header_layout, parent, false);
            
            return new ViewHolderHeader(view);
        } else if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_layout, parent, false);
            
            return new ViewHolderItem(view);
        }
        
        throw new RuntimeException("View type not available");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolderHeader) {
            // cast holder to ViewHolderHeader 
        } else if (holder instanceof ViewHolderItem) {
            // cast holder to ViewHolderItem
            // item position starts at 1
            position--;
        }
    }

    @Override
    public int getItemCount() {
        // adding 1 for header view
        return items.size() + 1;
    }
    
    public class ViewHolderHeader extends RecyclerView.ViewHolder {

        public ViewHolderHeader(View itemView) {
            super(itemView);
        }
    }
    
    public class ViewHolderItem extends RecyclerView.ViewHolder {

        public ViewHolderItem(View itemView) {
            super(itemView);
        }
    }
}
~~~
