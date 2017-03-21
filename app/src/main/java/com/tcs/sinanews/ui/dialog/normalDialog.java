package com.tcs.sinanews.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tcs.sinanews.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pingfan.yang on 2017/3/21.
 */
public class normalDialog extends Dialog {
    @Bind(R.id.tv_dialog_content)
    TextView mTvDialogContent;
    @Bind(R.id.btn_dialog_yes)
    Button mBtnDialogYes;
    @Bind(R.id.btn_dialog_no)
    Button mBtnDialogNo;
    private DialogClick mClick;
    /**
     * @param context
     * @param content    dialog内容
     * @param no         dialog否定选项文字
     * @param yes        dialog确定选项文字
     * @param cancelable 是否能够取消
     */
    public normalDialog(Context context, String content, String no, String yes, boolean cancelable
            , DialogClick click) {
        super(context,R.style.custom_dialog);
        setContentView(R.layout.dialog);
        ButterKnife.bind(this);
        setCancelable(cancelable);
        mTvDialogContent.setText(content);
        mBtnDialogNo.setText(no);
        mBtnDialogYes.setText(yes);
        mClick = click;
    }


    @OnClick({R.id.btn_dialog_yes, R.id.btn_dialog_no})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_dialog_yes:
                mClick.clickYes();
                break;
            case R.id.btn_dialog_no:
                mClick.clickNo();
                break;
        }
    }
}
