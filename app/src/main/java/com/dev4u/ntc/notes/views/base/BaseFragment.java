package com.dev4u.ntc.notes.views.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dev4u.ntc.notes.R;
import com.dev4u.ntc.notes.presenter.BaseView;
import com.dev4u.ntc.notes.utils.ClickUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * IDE: Android Studio
 * Created by Nguyen Trong Cong  - 2DEV4U.COM
 * Name packge: com.dev4u.ntc.notes.view.base
 * Name project: Notes
 * Date: 3/8/2017
 * Time: 20:47
 */

public abstract class BaseFragment extends Fragment implements BaseView {
    protected final String TAG = this.getClass().getSimpleName();
    @BindView(R.id.mTvTitleActionbar)
    TextView mTvTitle;
    @BindView(R.id.mImgLeft)
    ImageView mImgLeft;
    private ProgressDialog mProgressDialog;

    protected abstract String getTitle();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onViewReady(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ButterKnife.bind(getActivity());
        mTvTitle.setText(getTitle());
        mImgLeft.setBackgroundResource(R.drawable.ic_btn_back);
        getView().findViewById(R.id.mRlImgLeft).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ClickUtil.isLocked) return;
                ClickUtil.lock();

                getActivity().onBackPressed();
            }
        });
    }

    @CallSuper
    protected void onViewReady(Bundle savedInstanceState) {
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setMessage(getString(R.string.message_waiting));
        mProgressDialog.setCancelable(false);
    }

    public void showToastLong(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showToastShort(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
