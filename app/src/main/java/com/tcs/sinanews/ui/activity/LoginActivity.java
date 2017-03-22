package com.tcs.sinanews.ui.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.tcs.sinanews.Constant;
import com.tcs.sinanews.MyApplication;
import com.tcs.sinanews.R;
import com.tcs.sinanews.ui.activity.interfaces.Ilogin;
import com.tcs.sinanews.utils.EventBusHelper;
import com.tcs.sinanews.utils.StringUtils;
import com.tcs.sinanews.utils.ToastUtil;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by hzypf on 2017/3/22.
 */

public class LoginActivity extends BaseActivity implements Ilogin {
    @Bind(R.id.et_username)
    EditText mEtUsername;
    @Bind(R.id.et_password)
    EditText mEtPassword;
    @Bind(R.id.btn_login)
    Button mBtnLogin;
    @Bind(R.id.btn_register)
    Button mBtnRegister;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initToolBar();
        initEt();
    }

    private void initEt() {
        mEtUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!StringUtils.isEmpty(s.toString()) &&
                        !StringUtils.isEmpty(mEtPassword.getText().toString())) {
                    mBtnLogin.setEnabled(true);
                    mBtnRegister.setEnabled(true);
                    mBtnLogin.setBackgroundResource(R.color.toolbar);
                    mBtnRegister.setBackgroundResource(R.color.btn_password);
                }
            }
        });
        mEtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!StringUtils.isEmpty(s.toString()) &&
                        !StringUtils.isEmpty(mEtPassword.getText().toString())) {
                    mBtnLogin.setEnabled(true);
                    mBtnRegister.setEnabled(true);
                    mBtnLogin.setBackgroundResource(R.color.toolbar);
                    mBtnRegister.setBackgroundResource(R.color.btn_password);
                }
            }
        });
    }

    private void initToolBar() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    protected boolean applySystemBarDrawable() {
        return false;
    }

    @Override
    protected int getTitleResId() {
        return Constant.Titles[1];
    }

    @Override
    protected boolean needToolBarButton() {
        return true;
    }


    @OnClick({R.id.btn_login, R.id.btn_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.btn_register:
                //注册失败会抛出HyphenateException
                try {
                    EMClient.getInstance().createAccount(getUserName(),
                            getPassWord());//同步方法
                    ToastUtil.showToastLong(mContext,"注册成功");
                } catch (HyphenateException e) {
                    ToastUtil.showToastLong(mContext, "注册失败，请重试");
                }

                break;
        }
    }

    private void login() {
        EMClient.getInstance().login(getUserName(), getPassWord(), new EMCallBack() {
            @Override
            public void onSuccess() {
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();
                ToastUtil.showToastOnUi(mContext,"登录成功");
                MyApplication.setUserName(getUserName());
                EventBusHelper.post("","update");
            }

            @Override
            public void onError(int i, String s) {
                ToastUtil.showToastOnUi(mContext,"登陆失败");
                MyApplication.setUserName("");
                Log.e("loginActivity:",s);
            }

            @Override
            public void onProgress(int i, String s) {

            }
        });
    }

    @Override
    public String getUserName() {
        return mEtUsername.getText().toString();
    }

    @Override
    public String getPassWord() {
        return mEtPassword.getText().toString();
    }
}
