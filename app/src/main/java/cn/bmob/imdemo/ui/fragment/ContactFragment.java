package cn.bmob.imdemo.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.andoop.ctrlf5.bangzhu.R;
import com.andoop.ctrlf5.bangzhu.modle.BzUser;
import com.andoop.ctrlf5.bangzhu.modle.CareList;
import com.andoop.ctrlf5.bangzhu.presenter.ContactViewPresenter;
import com.andoop.ctrlf5.bangzhu.view.PersonZiLiaoActivity;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.imdemo.adapter.ContactAdapter;
import cn.bmob.imdemo.adapter.OnRecyclerViewListener;
import cn.bmob.imdemo.adapter.base.IMutlipleItem;
import cn.bmob.imdemo.base.ParentWithNaviActivity;
import cn.bmob.imdemo.base.ParentWithNaviFragment;
import cn.bmob.imdemo.bean.Friend;
import cn.bmob.imdemo.bean.User;
import cn.bmob.imdemo.event.RefreshEvent;
import cn.bmob.imdemo.model.UserModel;
import cn.bmob.imdemo.ui.ChatActivity;
import cn.bmob.imdemo.ui.NewFriendActivity;
import cn.bmob.imdemo.ui.SearchUserActivity;
import cn.bmob.newim.BmobIM;
import cn.bmob.newim.bean.BmobIMConversation;
import cn.bmob.newim.bean.BmobIMUserInfo;
import cn.bmob.v3.listener.DeleteListener;
import cn.bmob.v3.listener.FindListener;

/**联系人界面
 * @author :smile
 * @project:ContactFragment
 * @date :2016-04-27-14:23
 */
public class ContactFragment extends ParentWithNaviFragment {

    @Bind(R.id.rc_view)
    RecyclerView rc_view;
    @Bind(R.id.sw_refresh)
    SwipeRefreshLayout sw_refresh;
    ContactAdapter adapter;
    LinearLayoutManager layoutManager;
    ContactViewPresenter contactViewPresenter;
    CareList mCareList;

    @Override
    protected String title() {
        return "我的关注";
    }

    @Override
    public Object right() {
        return R.drawable.base_action_bar_add_bg_selector;
    }

    @Override
    public ParentWithNaviActivity.ToolBarListener setToolBarListener() {
        return new ParentWithNaviActivity.ToolBarListener() {
            @Override
            public void clickLeft() {

            }

            @Override
            public void clickRight() {
                startActivity(SearchUserActivity.class,null);
            }
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView =inflater.inflate(R.layout.fragment_conversation, container, false);
        initNaviView();
        ButterKnife.bind(this, rootView);

        contactViewPresenter=new ContactViewPresenter(this);
//        IMutlipleItem<Friend> mutlipleItem = new IMutlipleItem<Friend>() {
//
//            @Override
//            public int getItemViewType(int postion, Friend friend) {
//                if(postion==0){
//                    return ContactAdapter.TYPE_NEW_FRIEND;
//                }else{
//                    return ContactAdapter.TYPE_ITEM;
//                }
//            }
//
//            @Override
//            public int getItemLayoutId(int viewtype) {
//                if(viewtype== ContactAdapter.TYPE_NEW_FRIEND){
//                    return R.layout.header_new_friend;
//                }else{
//                    return R.layout.item_contact;
//                }
//            }
//
//            @Override
//            public int getItemCount(List<Friend> list) {
//                return list.size()+1;
//            }
//        };
        //adapter = new ContactAdapter(getActivity(),null,null);

        contactViewPresenter.loadData(BzUser.getCurentuser().getUserinfo().getUid()+"");


        rc_view.setAdapter(new ContactAdapter());
        layoutManager = new LinearLayoutManager(getActivity());
        rc_view.setLayoutManager(layoutManager);
        sw_refresh.setEnabled(true);

        sw_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mCareList=null;
                contactViewPresenter.loadData(BzUser.getCurentuser().getUserinfo().getUid()+"");
            }
        });

       // setListener();
        return rootView;
    }

    public void loading(){
        sw_refresh.setRefreshing(true);
    }
    public void success(CareList data){
        sw_refresh.setRefreshing(false);
        mCareList=data;
        rc_view.getAdapter().notifyDataSetChanged();



    }

    public void onError(String err){
        sw_refresh.setRefreshing(false);
        Toast.makeText(getActivity(),err, Toast.LENGTH_SHORT).show();
    }

    public class ContactAdapter extends RecyclerView.Adapter<ContactViewHolder>{
        @Override
        public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ContactViewHolder(View.inflate(getActivity(),R.layout.item_contact,null));
        }
        @Override
        public void onBindViewHolder(ContactViewHolder holder, int position) {

            CareList.CareListBean careListBean = mCareList.getCare_list().get(position);
            holder.name.setText(careListBean.getName());
            ImageLoader.getInstance().displayImage(careListBean.getAvatar(),holder.icon);
            holder.icon.setTag(careListBean.getUid());
            holder.rl_contact.setTag(careListBean);


        }

        @Override
        public int getItemCount() {
            return mCareList==null?0:mCareList.getCare_list().size();
        }
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder{

        public TextView name;
        public ImageView icon;
        public RelativeLayout rl_contact;
        public ContactViewHolder(View itemView) {
            super(itemView);

            name= (TextView) itemView.findViewById(R.id.tv_recent_name);
            icon= (ImageView) itemView.findViewById(R.id.iv_recent_avatar);
            rl_contact= (RelativeLayout) itemView.findViewById(R.id.rl_contact);

            icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), PersonZiLiaoActivity.class);
                    Bundle extra=new Bundle();
                    extra.putString("id",v.getTag().toString());
                    intent.putExtras(extra);
                    startActivity(intent);
                }
            });

            rl_contact.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CareList.CareListBean careListBean = (CareList.CareListBean) v.getTag();
                    BmobIMUserInfo info = new BmobIMUserInfo(careListBean.getUid()+"",careListBean.getName(),careListBean.getAvatar());
                    //启动一个会话，实际上就是在本地数据库的会话列表中先创建（如果没有）与该用户的会话信息，且将用户信息存储到本地的用户表中
                    BmobIMConversation c = BmobIM.getInstance().startPrivateConversation(info, null);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("c", c);
                    startActivity(ChatActivity.class, bundle);
                }
            });

        }
    }


    private void setListener(){
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                rootView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                sw_refresh.setRefreshing(true);
                query();
            }
        });
        sw_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                query();
            }
        });
//        adapter.setOnRecyclerViewListener(new OnRecyclerViewListener() {
//            @Override
//            public void onItemClick(int position) {
//                if (position == 0) {//跳转到新朋友页面
//                    startActivity(NewFriendActivity.class, null);
//                } else {
//                    Friend friend = adapter.getItem(position);
//                    User user = friend.getFriendUser();
//                    BmobIMUserInfo info = new BmobIMUserInfo(user.getObjectId(), user.getUsername(), user.getAvatar());
//                    //启动一个会话，实际上就是在本地数据库的会话列表中先创建（如果没有）与该用户的会话信息，且将用户信息存储到本地的用户表中
//                    BmobIMConversation c = BmobIM.getInstance().startPrivateConversation(info, null);
//                    Bundle bundle = new Bundle();
//                    bundle.putSerializable("c", c);
//                    startActivity(ChatActivity.class, bundle);
//                }
//            }
//
//            @Override
//            public boolean onItemLongClick(final int position) {
//                log("长按" + position);
//                if(position==0){
//                    return true;
//                }
//                UserModel.getInstance().deleteFriend(adapter.getItem(position), new DeleteListener() {
//                    @Override
//                    public void onSuccess() {
//                        toast("好友删除成功");
//                        adapter.remove(position);
//                    }
//
//                    @Override
//                    public void onFailure(int i, String s) {
//                        toast("好友删除失败：" + i + ",s =" + s);
//                    }
//                });
//                return true;
//            }
//        });
    }

    @Override
    public void onResume() {
        super.onResume();
       /* sw_refresh.setRefreshing(true);
        query();*/
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    /**注册自定义消息接收事件
     * @param event
     */
    @Subscribe
    public void onEventMainThread(RefreshEvent event){
        //重新刷新列表
        log("---联系人界面接收到自定义消息---");
       /// adapter.notifyDataSetChanged();
    }

    /**
      查询本地会话
     */
    public void query(){
       /* UserModel.getInstance().queryFriends(new FindListener<Friend>() {
            @Override
            public void onSuccess(List<Friend> list) {
                adapter.bindDatas(list);
                adapter.notifyDataSetChanged();
                sw_refresh.setRefreshing(false);
            }

            @Override
            public void onError(int i, String s) {
                adapter.bindDatas(null);
                adapter.notifyDataSetChanged();
                sw_refresh.setRefreshing(false);
            }
        });*/
    }

}
