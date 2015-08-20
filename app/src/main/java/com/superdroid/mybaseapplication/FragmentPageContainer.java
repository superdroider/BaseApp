package com.superdroid.mybaseapplication;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

import com.superdroid.mybaseapplication.utils.Constants;
import com.superdroid.mybaseapplication.utils.UIUtil;

/**
 * Created by GT on 2015/8/20.
 * �������ҳ�������
 */
public abstract class FragmentPageContainer extends FrameLayout {

    private int currentState = Constants.PAGE_UNKNOWN;

    private View loadingPage;
    private View errorPage;
    private View emptyPage;
    private View successPage;


    public FragmentPageContainer(Context context) {
        super(context);
        initPage();
    }

    /**
     * ��ʼ��ҳ��
     */
    private void initPage() {
        loadingPage = createLoadingPage();
        if (loadingPage != null) {
            this.addView(loadingPage, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        }

        errorPage = createErrorPage();
        if (errorPage != null) {
            this.addView(errorPage, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        }

        emptyPage = createEmptyPage();
        if (emptyPage != null) {
            this.addView(emptyPage, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        }

        successPage = createSuccessPage();

        showPageByState();
    }

    /**
     * ����ҳ��״̬��ʾ��Ӧ��ҳ��
     */
    private void showPageByState() {
        if (loadingPage != null) {
            loadingPage.setVisibility((currentState == Constants.PAGE_UNKNOWN || currentState == Constants.PAGE_LOADING) ? View.VISIBLE : View.GONE);
        }
        if (errorPage != null) {
            errorPage.setVisibility(currentState == Constants.PAGE_ERROR ? View.VISIBLE : View.GONE);
        }
        if (emptyPage != null) {
            emptyPage.setVisibility(currentState == Constants.PAGE_EMPTY ? View.VISIBLE : View.GONE);
        }
        if (currentState == Constants.PAGE_SUCCESS && successPage == null) {
            successPage = createSuccessPage();
            this.addView(successPage, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        }
    }

    /**
     * ����������ҳ��
     *
     * @return
     */
    private View createLoadingPage() {
        return UIUtil.inflate(R.layout.page_loading);
    }

    /**
     * ��������ҳ��
     *
     * @return
     */
    private View createErrorPage() {
        return UIUtil.inflate(R.layout.page_error);
    }

    /**
     * �����հ�ҳ��
     *
     * @return
     */
    private View createEmptyPage() {
        return UIUtil.inflate(R.layout.page_empty);
    }

    /**
     * �����ɹ�ҳ��
     *
     * @return
     */
    protected abstract View createSuccessPage();

    /**
     * �ⲿ���ã���ʾFragmentҳ��
     */
    public void show() {
        if (currentState == Constants.PAGE_EMPTY || currentState == Constants.PAGE_ERROR) {
            currentState = Constants.PAGE_UNKNOWN;
        }
        if (currentState == Constants.PAGE_UNKNOWN) {
            currentState = Constants.PAGE_LOADING;
        }

        showPageByState();
    }
}
