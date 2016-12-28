package com.example.nghia.lab06.fragments;


import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.example.nghia.lab06.R;
import com.example.nghia.lab06.adapters.ColorChoosingSpinnerAdapter;
import com.example.nghia.lab06.events.OnReceiveNewTodoEvent;
import com.example.nghia.lab06.models.ColorChoosingItem;
import com.example.nghia.lab06.models.TodoModel;
import com.example.nghia.lab06.utils.ServiceContext;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddTodoDialogFragment extends DialogFragment{
    private static final String STARTING_MODEL_KEY = "editing model";
    private static final String ACTION_KEY = "action";
    private static final String TOKEN_KEY = "token";

    public static final int ACTION_ADD = 1;
    public static final int ACTION_EDIT = 2;
    private static final String POSITION_KEY = "adapter position";
    private static final String TAG = AddTodoDialogFragment.class.toString();


    @BindView(R.id.spn_color_chooser)
    Spinner spinner;
    @BindView(R.id.rl_add_todo_dialog)
    RelativeLayout rlcontainer;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.et_title)
    EditText etTitle;
    @BindView(R.id.rbtn_completed)
    RadioButton rbtnIsCompleted;
    @BindView(R.id.rbtn_in_progress)
    RadioButton rbtnInProgress;
    @BindView(R.id.rg_is_completed)
    RadioGroup radioGroup;


    private TodoModel startingModel;
    private int action;
    private String token;
    private int position;

    public AddTodoDialogFragment() {
        // Required empty public constructor
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_add_todo_dialog, container, false);
        ButterKnife.bind(this, rootView);
        loadArguments();
        setupUI();
        return rootView;
    }

    private void loadArguments() {
        Bundle args = getArguments();
        startingModel = (TodoModel) args.getSerializable(STARTING_MODEL_KEY);
        if (startingModel == null) {
            startingModel = new TodoModel();
        }
        action = args.getInt(ACTION_KEY, ACTION_ADD);
        token = args.getString(TOKEN_KEY, null);
        if (action == ACTION_EDIT) {
            position = args.getInt(POSITION_KEY, -1);
        }
    }

    private void setupUI() {
        etContent.setText(startingModel.getContent());
        etTitle.setText(startingModel.getTitle());
        if (startingModel.isCompleted()) {
            radioGroup.check(R.id.rbtn_completed);
        } else {
            radioGroup.check(R.id.rbtn_in_progress);
        }
        spinner.setAdapter((new ColorChoosingSpinnerAdapter(getContext()
                , R.layout.color_choose_spinner_item
                , ColorChoosingItem.colors)));
//        ColorChoosingItem item = (ColorChoosingItem) spinner.getAdapter().getItem(0);
//        rlcontainer.setBackgroundColor(Color.parseColor(item.getColorString()));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                ColorChoosingItem item = (ColorChoosingItem) spinner.getAdapter().getItem(position);
                rlcontainer.setBackgroundColor(Color.parseColor(item.getColorString()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @OnClick(R.id.imv_confirm_add_todo)
    public void confirm() {
//        Toast.makeText(getContext(), "confirmed", Toast.LENGTH_SHORT).show();
        ColorChoosingItem item = ((ColorChoosingItem) spinner.getAdapter().getItem(spinner.getLastVisiblePosition()));
        final TodoModel confirmedTodo = startingModel;
        confirmedTodo.setContent(etContent.getText().toString());
        confirmedTodo.setCompleted(rbtnIsCompleted.isChecked());
        confirmedTodo.setColor(item.getColorString());
        confirmedTodo.setTitle(etTitle.getText().toString());
        Log.d(TAG, String.format("confirmed todoModel  = %s", confirmedTodo));
        switch (action) {
            case ACTION_ADD:
                ServiceContext.getInstance().createTodo(token, confirmedTodo, new ServiceContext.ServiceTodoReceivedListener() {
                    @Override
                    public void onResponse(TodoModel model) {
                        Log.d(TAG, String.format("onResponse Action Add: responseModel = %s", model));
                        model.setCompleted(confirmedTodo.isCompleted());
                        EventBus.getDefault().post(new OnReceiveNewTodoEvent(model,OnReceiveNewTodoEvent.Action.ADD ));
                        AddTodoDialogFragment.this.dismiss();
                    }
                });
                break;
            case ACTION_EDIT:
                ServiceContext.getInstance().editTodo(token, confirmedTodo, new ServiceContext.ServiceTodoReceivedListener() {
                    @Override
                    public void onResponse(TodoModel startingModel) {
                        Log.d(TAG, String.format("onResponse Action Edit: responseModel = %s", startingModel));
                        EventBus.getDefault().post(
                                new OnReceiveNewTodoEvent(confirmedTodo,startingModel, OnReceiveNewTodoEvent.Action.EDIT,position));
                        AddTodoDialogFragment.this.dismiss();
                    }
                });

                break;
        }
//        EventBus.getDefault().post(new OnConfirmAddNewTodoEvent(confirmedTodo));
//        Toast.makeText(getContext(), String.format("current color %s",
//                ((ColorChoosingItem)spinner.getAdapter().getItem(spinner.getLastVisiblePosition()))
//                        .getColorName()), Toast.LENGTH_SHORT).show();
//        this.dismiss();
    }

    public static AddTodoDialogFragment createAdd(String token) {
        Bundle args = new Bundle();
        args.putSerializable(STARTING_MODEL_KEY, new TodoModel());
        args.putInt(ACTION_KEY, ACTION_ADD);
        args.putString(TOKEN_KEY, token);
        AddTodoDialogFragment fragment = new AddTodoDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static AddTodoDialogFragment createEditFromArrayList(String token, int position,TodoModel startingModel) {
        Bundle args = new Bundle();
        args.putInt(ACTION_KEY, ACTION_EDIT);
        args.putString(TOKEN_KEY, token);
        args.putSerializable(STARTING_MODEL_KEY, startingModel);
        args.putInt(POSITION_KEY, position);
        AddTodoDialogFragment fragment = new AddTodoDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
