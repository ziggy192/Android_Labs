package com.example.nghia.lab06;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.nghia.lab06.adapters.TodoFragmentPagerAdapter;
import com.example.nghia.lab06.events.OnGetTodoListEvent;
import com.example.nghia.lab06.events.OnReceiveNewTodoEvent;
import com.example.nghia.lab06.fragments.CompletedTodoListFragment;
import com.example.nghia.lab06.fragments.ShowingTodoListFragment;
import com.example.nghia.lab06.models.TodoModel;
import com.example.nghia.lab06.utils.ServiceContext;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Iterator;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ToDoActivity extends AppCompatActivity {

    public static final String TOKEN_KEY = "token";
    public static final String TODO_MODELS_KEY = "todoModelArrayList";
    private static final String TAG = ToDoActivity.class.toString();
    public static final String TYPE_KEY = "fragment title";


    @BindView(R.id.vp_container)
    ViewPager viewPager;
    @BindView(R.id.tl_container)
    TabLayout tabLayout;

    private ShowingTodoListFragment inProgressFragment;
    private ShowingTodoListFragment completedFragment;
    private String token;
    private ArrayList<TodoModel> inProgressTodoModels;
    private ArrayList<TodoModel> completedTodoModels;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);
        ButterKnife.bind(this);
        getData();
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.fl_container, ShowingTodoListFragment.createAdd(token))
//                .commit();

    }

    private void setupUI() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        inProgressFragment = ShowingTodoListFragment.create(token, inProgressTodoModels, ShowingTodoListFragment.TYPE_PROGRESS);
        completedFragment = ShowingTodoListFragment.create(token, completedTodoModels, ShowingTodoListFragment.TYPE_COMPLETED);
        fragments.add(inProgressFragment);
        fragments.add(completedFragment);
//        fragments.add(new CompletedTodoListFragment());
        viewPager.setAdapter(
                new TodoFragmentPagerAdapter(getSupportFragmentManager(), fragments));
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);

    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(sticky = true)
    public void loadTodoModel(OnGetTodoListEvent event) {
        ArrayList<TodoModel> todoModels = event.getTodoModels();
        Iterator<TodoModel> iterator = todoModels.iterator();
        inProgressTodoModels = new ArrayList<>();
        completedTodoModels = new ArrayList<>();
        while (iterator.hasNext()) {
            TodoModel model = iterator.next();
            if (model.isCompleted()) {
                completedTodoModels.add(model);
            } else {
                inProgressTodoModels.add(model);
            }
        }
        setupUI();
    }


    @Subscribe
    public void onExchangeTodoModels(OnReceiveNewTodoEvent event) {
        OnReceiveNewTodoEvent.Action action = event.getAction();
        switch (action) {
            case ADD:
                if (event.getTodoModel().isCompleted()) {
                    insertNewModel(completedFragment, event.getTodoModel());
                } else {
                    insertNewModel(inProgressFragment, event.getTodoModel());
                }
                break;
            case EDIT:
                if (event.getTodoModel().isCompleted() != event.getOldTodoModel().isCompleted()) {
                    if (event.getOldTodoModel().isCompleted()) {
                        // exchange from completed to progress
                        exchangeTodo(completedFragment, inProgressFragment, event.getAdapterPosition());
                    } else {
                        // exchange from progress to complete
                        exchangeTodo(inProgressFragment, completedFragment, event.getAdapterPosition());
                    }
                } else {
                    if (event.getTodoModel().isCompleted()) {
                        completedFragment.getAdapter().notifyItemChanged(event.getAdapterPosition());

                    } else {
                        inProgressFragment.getAdapter().notifyItemChanged(event.getAdapterPosition());
                    }
                }

//                    if (event.getTodoModel().isCompleted() == event.getOldTodoModel().isCompleted()) {
//                        //no need to exchange model
//
//                    } else {
//
//                    }
                break;

        }

    }

    private void exchangeTodo(ShowingTodoListFragment srcFragment, ShowingTodoListFragment desFragment,int adapterPosition) {

        Log.d(TAG, String.format("onExchangeTodo: adapterPosition = %s", adapterPosition));
        Log.d(TAG, String.format("onExchangeTodo: exchange from %s to %s ", srcFragment, desFragment));
        TodoModel model = srcFragment.getAdapter().getNoteList().get(adapterPosition);
        insertNewModel(desFragment,model);
        removeOldModel(srcFragment,adapterPosition);

    }
    private void insertNewModel(ShowingTodoListFragment fragment, TodoModel model) {
        fragment.getAdapter().getNoteList().add(model);
        fragment.getAdapter().notifyItemInserted(fragment.getAdapter().getItemCount()-1);
//        fragment.getAdapter().notifyDataSetChanged();
    }

    private void removeOldModel(ShowingTodoListFragment fragment, int adapterPosition) {
        fragment.getAdapter().getNoteList().remove(adapterPosition);
        fragment.getAdapter().notifyItemRemoved(adapterPosition);
//        fragment.getAdapter().notifyDataSetChanged();
    }


    public void getData() {
        token = getIntent().getStringExtra(TOKEN_KEY);
        ServiceContext.getInstance().getTodos(token);
    }



}
