package com.yiqirong.androidbaseframework.fragment;


import com.yiqirong.androidbaseframework.R;

/**
 * Created by kangwencai on 2016/11/21.
 */

public class IndicateFragment extends BaseFragment {


    @Override
    protected int setContentViewId() {
        return R.layout.fragment_indicate;
    }


    @Override
    protected void initViewData() {
//        float unSelectSize = 12;
//        float selectSize = unSelectSize * 1.3f;
//        moretabIndicator.setOnTransitionListener(new OnTransitionTextListener().setColor(0xFF2196F3, Color.GRAY).setSize(selectSize, unSelectSize));
//
//        moretabIndicator.setScrollBar(new ColorBar(getContext(), 0xFF2196F3, 4));
//
//        moretabViewPager.setOffscreenPageLimit(2);
//        viewLinker = new ViewLinker(moretabIndicator, moretabViewPager);
//        List<Fragment> mList = new ArrayList<>();
//        mList.add(new RefreshFragment());
//        mList.add(new RefreshFragment());
//        viewLinker.setAdapter(new ViewPagerAdapter(getChildFragmentManager(), mList));
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void lazyLoad() {

    }

//    private class ViewPagerAdapter extends ViewLinker.IndicatorFragmentPagerAdapter {
//
//        private String[] versions = {"Cupcake", "Donut", "Éclair", "Froyo", "Gingerbread", "Honeycomb", "Ice Cream Sandwich", "Jelly Bean", "KitKat", "Lolipop", "Marshmallow"};
//        private List<Fragment> mList;
//
//        public ViewPagerAdapter(FragmentManager fragmentManager, List<Fragment> mList) {
//            super(fragmentManager);
//            this.mList = mList;
//        }
//
//        @Override
//        public int getCount() {
//            return mList.size();
//        }
//
//        @Override
//        public View getViewForTab(int position, View convertView, ViewGroup container) {
//            if (convertView == null) {
//                convertView = LayoutInflater.from(getContext()).inflate(R.layout.indicate_view, container, false);
//
//            }
//            TextView textView = (TextView) convertView;
//            textView.setText(versions[position]);
//
//            int witdh = getTextWidth(textView);
//            int padding = DisplayUtil.dipToPix(getContext().getApplicationContext(), 8);
//            //因为wrap的布局 字体大小变化会导致textView大小变化产生抖动，这里通过设置textView宽度就避免抖动现象
//            //1.3f是根据上面字体大小变化的倍数1.3f设置
//            textView.setWidth((int) (witdh * 1.3f) + padding);
//
//            return convertView;
//        }
//
//        @Override
//        public Fragment getFragmentForPage(int position) {
//            return mList.get(position);
//        }
//
//        private int getTextWidth(TextView textView) {
//            if (textView == null) {
//                return 0;
//            }
//            Rect bounds = new Rect();
//            String text = textView.getText().toString();
//            Paint paint = textView.getPaint();
//            paint.getTextBounds(text, 0, text.length(), bounds);
//            int width = bounds.left + bounds.width();
//            return width;
//        }
//    }
//    private class MyAdapter extends ViewLinker.IndicatorViewPagerAdapter {
//        private String[] versions = {"Cupcake", "Donut", "Éclair", "Froyo", "Gingerbread", "Honeycomb", "Ice Cream Sandwich", "Jelly Bean", "KitKat", "Lolipop", "Marshmallow"};
//        private String[] names = {"纸杯蛋糕", "甜甜圈", "闪电泡芙", "冻酸奶", "姜饼", "蜂巢", "冰激凌三明治", "果冻豆", "奇巧巧克力棒", "棒棒糖", "棉花糖"};
//
//        @Override
//        public int getCount() {
//            return versions.length;
//        }
//
//        @Override
//        public View getViewForTab(int position, View convertView, ViewGroup container) {
//            if (convertView == null) {
//                convertView = LayoutInflater.from(getContext()).inflate(R.layout.indicate_view, container, false);
//
//            }
//            TextView textView = (TextView) convertView;
//            textView.setText(versions[position]);
//
//            int witdh = getTextWidth(textView);
//            int padding = DisplayUtil.dipToPix(getContext().getApplicationContext(), 8);
//            //因为wrap的布局 字体大小变化会导致textView大小变化产生抖动，这里通过设置textView宽度就避免抖动现象
//            //1.3f是根据上面字体大小变化的倍数1.3f设置
//            textView.setWidth((int) (witdh * 1.3f) + padding);
//
//            return convertView;
//        }
//
//        @Override
//        public View getViewForPage(int position, View convertView, ViewGroup container) {
//            if (convertView == null) {
//                convertView = new TextView(container.getContext());
//            }
//            TextView textView = (TextView) convertView;
//            textView.setText(names[position]);
//            textView.setGravity(Gravity.CENTER);
//            textView.setTextColor(Color.GRAY);
//            return convertView;
//        }
//
//        @Override
//        public int getItemPosition(Object object) {
//            //这是ViewPager适配器的特点,有两个值 POSITION_NONE，POSITION_UNCHANGED，默认就是POSITION_UNCHANGED,
//            // 表示数据没变化不用更新.notifyDataChange的时候重新调用getViewForPage
//            return PagerAdapter.POSITION_UNCHANGED;
//        }
//
//        private int getTextWidth(TextView textView) {
//            if (textView == null) {
//                return 0;
//            }
//            Rect bounds = new Rect();
//            String text = textView.getText().toString();
//            Paint paint = textView.getPaint();
//            paint.getTextBounds(text, 0, text.length(), bounds);
//            int width = bounds.left + bounds.width();
//            return width;
//        }
//
//    }

}
