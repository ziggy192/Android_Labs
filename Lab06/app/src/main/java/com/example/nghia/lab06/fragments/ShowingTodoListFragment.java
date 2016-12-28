package com.example.nghia.lab06.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.nghia.lab06.R;
import com.example.nghia.lab06.ToDoActivity;
import com.example.nghia.lab06.adapters.TodoRecyclerViewAdapter;
import com.example.nghia.lab06.events.OnGetTodoListEvent;
import com.example.nghia.lab06.events.OnReceiveNewTodoEvent;
import com.example.nghia.lab06.models.TodoModel;
import com.example.nghia.lab06.utils.ServiceContext;
import com.example.nghia.lab06.view_holder.TodoViewHolder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowingTodoListFragment extends Fragment {

    private static final String TAG = ToDoActivity.class.toString();
    public static final int TYPE_PROGRESS = 1;
    public static final int TYPE_COMPLETED = 2;

    @BindView(R.id.rv_todo_list)
    RecyclerView rvTodoList;
    TodoRecyclerViewAdapter adapter;
    ArrayList<TodoModel> todoModels;
    private String token;
    private int type;

    public ShowingTodoListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_showing_todo_list, container, false);
        ButterKnife.bind(this,rootView);
        loadArguments();
        setupUI();
        return rootView;
    }

    private void loadArguments() {
        Bundle args = getArguments();
        token = args.getString(ToDoActivity.TOKEN_KEY);
        todoModels = (ArrayList<TodoModel>) args.getSerializable(ToDoActivity.TODO_MODELS_KEY);
        type = args.getInt(ToDoActivity.TYPE_KEY,TYPE_PROGRESS);
    }

    public static ShowingTodoListFragment create(String token,ArrayList<TodoModel> todoModels,int type) {
        Bundle args = new Bundle();
        args.putString(ToDoActivity.TOKEN_KEY, token);
        args.putSerializable(ToDoActivity.TODO_MODELS_KEY, todoModels);
        args.putInt(ToDoActivity.TYPE_KEY, type);
        ShowingTodoListFragment fragment = new ShowingTodoListFragment();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);

    }

    private void setupUI() {
//        ServiceContext.getInstance().getTodos(token);
//        todoModels = new ArrayList<>();
        adapter = new TodoRecyclerViewAdapter(todoModels);
        rvTodoList.setAdapter(adapter);
        rvTodoList.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

    }

//    @Subscribe(threadMode = ThreadMode.MAIN)
    //todo debugging
    public void updateUI(OnGetTodoListEvent event) {
        this.todoModels.clear();
        this.todoModels.addAll(event.getTodoModels());
        adapter.notifyDataSetChanged();
    }

    @OnClick(R.id.fab_add_todo)
    public void addTodo() {
        AddTodoDialogFragment.createAdd(token)
                .show(getActivity().getSupportFragmentManager(),null);
    }

    @Subscribe
    public void editTOdo(TodoViewHolder.OnItemViewClickedEvent event) {
        AddTodoDialogFragment.createEditFromArrayList(token,event.getPosition(), event.getTodoModel())
                .show(getFragmentManager(),null);
        EventBus.getDefault().cancelEventDelivery(event);
    }

//    @Subscribe
    public void addNewTodo(OnReceiveNewTodoEvent event) {
//        Toast.makeText(this.getActivity(), "notifying dataset changed", Toast.LENGTH_SHORT).show();

        OnReceiveNewTodoEvent.Action action = event.getAction();
        switch (action) {
            case ADD:
                if (modelMatchType(event.getTodoModel(),type)) {
                    this.adapter.getNoteList().add(event.getTodoModel());
                    adapter.notifyItemInserted(adapter.getItemCount()-1);
                }
                break;
            case EDIT:
                if (modelMatchType(event.getTodoModel(), type)) {
                    if (event.getTodoModel().isCompleted() == event.getOldTodoModel().isCompleted()) {
                        //no need to exchange model

                    } else {

                    }
                }
                break;

        }
//        Log.d(TAG, String.format("addNewTodo: todoModel = %s", event.getTodoModel()));
//        adapter.getNoteList().add(event.getTodoModel());
//        adapter.notifyItemInserted(adapter.getItemCount()-1);
    }

    private boolean modelMatchType(TodoModel todoModel,int type) {
        return (todoModel.isCompleted() && type == TYPE_COMPLETED)
                || (!todoModel.isCompleted() && type == TYPE_PROGRESS);
    }


    @Subscribe
    public void onAdapterDatasetChange(ServiceContext.OnAdapterDataSetChangeEvent event) {
        adapter.notifyDataSetChanged();
    }

    public TodoRecyclerViewAdapter getAdapter() {
        return adapter;
    }

    @Override
    public String toString() {
        switch (type) {
            case TYPE_PROGRESS:
                return "in progress";
            case TYPE_COMPLETED:
                return "completed";
            default:
                return "in progress";
        }
    }
}
