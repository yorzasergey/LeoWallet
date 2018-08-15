package com.xtreme.leowallet.activity_main;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xtreme.leowallet.R;
import com.xtreme.leowallet.adapter.AccountInfoRecyclerViewAdapter;
import com.xtreme.leowallet.adapter.MyDividerItemDecoration;
import com.xtreme.leowallet.model.AccountInfo;
import com.xtreme.leowallet.model.HeaderItem;
import com.xtreme.leowallet.model.ListItem;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainActivityView {

    private static final String JSON_FILE_NAME = "dashboard.json";
    private AccountInfoRecyclerViewAdapter mDashboardAdapter;
    private MainActivityPresenterImpl mMainActivityPresenter;
    private InputStream mInputStream;
    private List<ListItem> mListDashboard;

    @BindView(R.id.leoWalletAccount)
    TextView mTextViewAccountName;

    @BindView(R.id.textViewBalance)
    TextView mTextViewBalance;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.searchqueryHint)
    ConstraintLayout mSearchQueryHint;

    @BindView(R.id.searchViewLayout)
    SearchView mSearchView;

    @BindView(R.id.navigation)
    BottomNavigationView mBottomNavigationView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_purse:
                    Toast.makeText(MainActivity.this, getString(R.string.title_purse), Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_bonuses:
                    Toast.makeText(MainActivity.this, getString(R.string.title_bonuses), Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_history:
                    Toast.makeText(MainActivity.this, getString(R.string.title_history), Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_profile:
                    Toast.makeText(MainActivity.this, getString(R.string.title_profile), Toast.LENGTH_SHORT).show();
                    return true;
            }
            return false;
        }
    };

    private View.OnClickListener mListItemOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {

                case R.id.prevIcon:

                    Toast.makeText(MainActivity.this, (String)v.getTag(), Toast.LENGTH_SHORT).show();

                    break;

                case R.id.favoriteIcon:

                    Toast.makeText(MainActivity.this, "favorite icon", Toast.LENGTH_SHORT).show();

                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mMainActivityPresenter = new MainActyvityPresenter();

        mBottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        removeShiftMode(mBottomNavigationView);

        mSearchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){
                    mSearchQueryHint.setVisibility(View.VISIBLE);
                    mSearchView.onActionViewCollapsed();
                }
            }
        });

        mDashboardAdapter = new AccountInfoRecyclerViewAdapter(new ArrayList<ListItem>(), mListItemOnClickListener, this);

        mRecyclerView.addItemDecoration(new MyDividerItemDecoration(this, R.drawable.divider));
        mRecyclerView.setHasFixedSize(true);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mRecyclerView.setAdapter(mDashboardAdapter);

        try {
            mInputStream = getApplication().getAssets().open(JSON_FILE_NAME);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mMainActivityPresenter.attachView(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        mMainActivityPresenter.getListAccountInfo(mInputStream);
    }

    @SuppressLint("RestrictedApi")
    private void removeShiftMode(BottomNavigationView view) {
        //this will remove shift mode for bottom navigation view
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                item.setShiftingMode(false);
                // set once again checked value, so view will be updated
                item.setChecked(item.getItemData().isChecked());
            }

        } catch (NoSuchFieldException e) {
            Log.e("ERROR NO SUCH FIELD", "Unable to get shift mode field");
        } catch (IllegalAccessException e) {
            Log.e("ERROR ILLEGAL ALG", "Unable to change value of shift mode");
        }
    }

    @Override
    public void showData(AccountInfo value) {

        mListDashboard = new ArrayList<>();

        mListDashboard.addAll(value.getInvoices());
        mListDashboard.add(new HeaderItem(getString(R.string.groupe_favorite)));
        mListDashboard.addAll(value.getFavorites());
        mListDashboard.add(new HeaderItem(getString(R.string.groupe_last)));
        mListDashboard.addAll(value.getLast());

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getString(R.string.text_leo_wallet));
        stringBuilder.append(" ");
        stringBuilder.append(value.getAccount().replaceFirst("(\\d{2})(\\d{3})(\\d{3})(\\d{2})(\\d{2})", "$1 $2 $3 $4 $5"));

        SpannableString spannableString = new SpannableString(stringBuilder);

        spannableString.setSpan(new ForegroundColorSpan(mTextViewAccountName.getCurrentTextColor()), 0, 9, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(Color.WHITE), 9, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new StyleSpan(Typeface.BOLD), 9, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new RelativeSizeSpan(1.2f), 9, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        mTextViewAccountName.setText(spannableString);

        double balanceUAH = value.getBalance()/100.;

        SpannableString spannableStringBalance = new SpannableString(String.format(new Locale("uk", "UA"), "%,.2f", balanceUAH).replaceAll(",","."));

        spannableStringBalance.setSpan(new RelativeSizeSpan(0.5f), spannableStringBalance.length()-3, spannableStringBalance.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        mTextViewBalance.setText(spannableStringBalance);

        mDashboardAdapter.addItems(mListDashboard);
    }

    @OnClick(R.id.buttonReplenish)
    public void onClickReplenish(Button button) {
        Toast.makeText(this, getString(R.string.button_replenish_text), Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.buttonWithdraw)
    public void onClickWithdraw(Button button) {
        Toast.makeText(this, getString(R.string.button_withdraw_text), Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.actionTransfer)
    public void onClickTransfer(ImageView imageView) {
        Toast.makeText(this, getString(R.string.action_transfer_text), Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.actionPay)
    public void onClickPay(ImageView imageView) {
        Toast.makeText(this, getString(R.string.action_pay_text), Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.actionBill)
    public void onClickBill(ImageView imageView) {
        Toast.makeText(this, getString(R.string.action_bill_text), Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.searchqueryHint)
    public void onClickBill(ConstraintLayout searchHint) {
        searchHint.setVisibility(View.GONE);
        mSearchView.onActionViewExpanded();
        Toast.makeText(this, getString(R.string.search_query_hint_text), Toast.LENGTH_SHORT).show();
    }
}
