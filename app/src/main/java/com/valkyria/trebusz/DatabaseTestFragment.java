package com.valkyria.trebusz;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.valkyria.trebusz.DatabaseInterface;
import com.valkyria.trebusz.TrebuszDatabaseAdapter;

import java.util.List;


/**
 * Created by Kensaj on 28.10.2017.
 */

public class DatabaseTestFragment extends Fragment {

    private Button DBTestButton;


    private TrebuszDatabaseAdapter DbAdapter;
    private Cursor todoCursor;
    private List<DatabaseInterface> tasks;
    private DatabaseInterface listAdapter;

    public DatabaseTestFragment() {
    }

    public static DatabaseTestFragment newInstance() {
        return new DatabaseTestFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initListView();
        //initButtonsOnClickListeners();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_databsetest, container, false);
    }

    private void initListView() {
        // fillListViewData();
        // initListViewOnItemClick();
    }

   /* private void fillListViewData() {
        TrebuszDatabaseAdapter = new TrebuszDatabaseAdapter(getApplicationContext());
        TrebuszDatabaseAdapter.open();
        getAllTasks();
        listAdapter = new TodoTasksAdapter(this, tasks);
        lvTodos.setAdapter(listAdapter);
    }

    private void getAllTasks() {
        tasks = new ArrayList<Task>();
        todoCursor = getAllEntriesFromDb();
        updateTaskList();
    }

    private Cursor getAllEntriesFromDb() {
        todoCursor = DbAdapter.getAllRecords();
        if (todoCursor != null) {
            startManagingCursor(todoCursor);
            todoCursor.moveToFirst();
        }
        return todoCursor;
    }

    private void updateTaskList() {
        if (todoCursor != null && todoCursor.moveToFirst()) {
            do {
                long id = todoCursor.getLong(TodoDbAdapter.ID_COLUMN);
                String description = todoCursor.getString(TodoDbAdapter.DESCRIPTION_COLUMN);
                boolean completed = todoCursor.getInt(TodoDbAdapter.COMPLETED_COLUMN) > 0 ? true : false;
                tasks.add(new TodoTask(id, description, completed));
            } while (todoCursor.moveToNext());
        }
    }

    @Override
    protected void onDestroy() {
        if (todoDbAdapter != null)
            todoDbAdapter.close();
        super.onDestroy();
    } */


}
