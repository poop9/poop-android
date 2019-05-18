package com.poop9.poop.report

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.poop9.poop.R
import com.poop9.poop.vo.RankData
import kotlinx.android.synthetic.main.fragment_report.*


class ReportFragment : Fragment() {
    val daysEN = listOf("MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN")
    val daysKR = listOf("월요일", "화요일", "수요일", "목요일", "금요일", "토요일", "일요일")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_report2, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadChart()
        loadRankList()
        loadBestActivePoop()
    }

    private fun loadChart(){
        report_title_daily_value.text = String.format("%s회", mockValueDaily())
        report_title_weekly_value.text = String.format("%s회", mockValueWeekly())
        report_title_monthly_value.text = String.format("%s회", mockValueMonthly())
    }

    private fun loadRankList(){
        val rankAdapter = ReportRankAdapter(context!!, mockRankList())
        report_rank_list.adapter = rankAdapter
    }

    private fun loadBestActivePoop(){
        when(mockActivePoopDay()){
            "MON" -> report_title_pattern_date.text = getString(R.string.report_mon)
            "TUE" -> report_title_pattern_date.text = getString(R.string.report_tue)
            "WED" -> report_title_pattern_date.text = getString(R.string.report_wed)
            "THU" -> report_title_pattern_date.text = getString(R.string.report_thu)
            "FRI" -> report_title_pattern_date.text = getString(R.string.report_fri)
            "SAT" -> report_title_pattern_date.text = getString(R.string.report_sat)
            "SUN" -> report_title_pattern_date.text = getString(R.string.report_sun)
        }
    }

    private fun mockRankList() : List<RankData>{

        val list = listOf(
            RankData(1, "GROOT1", 100)
            , RankData(2, "GROOT", 100)
            , RankData(3, "GROOT", 100)
            , RankData(4, "GROOT", 100)
            , RankData(5, "GROOT", 100)
            , RankData(6, "GROOT", 100)
            , RankData(7, "GROOT", 100)
            , RankData(8, "GROOT", 100)
            , RankData(9, "GROOT", 100)
            , RankData(10, "ALAN", 100)
        )
        return list
    }

    private fun mockActivePoopDay(): String{
        return "WED"
    }
    private fun mockValueDaily(): Int{
        return 4
    }
    private fun mockValueWeekly(): Int{
        return 12
    }
    private fun mockValueMonthly(): Int{
        return 78
    }
    /*
    private fun testChart() {
        val entries = ArrayList<Entry>()
        entries.add(Entry(0f, 1f))
        entries.add(Entry(1f, 2f))
        entries.add(Entry(2f, 3f))
        entries.add(Entry(3f, 4f))

        val lineDataSet = LineDataSet(entries, "total")// entries와 지정할 label을 생성자에 넘겨준다.
        lineDataSet.lineWidth = 2f // 선 굵기
        lineDataSet.circleRadius = 6f // 곡률
        lineDataSet.setCircleColor(ContextCompat.getColor(context!!, R.color.lightish_blue))
        lineDataSet.circleHoleColor = ContextCompat.getColor(context!!, R.color.lightish_blue)
        lineDataSet.color = ContextCompat.getColor(context!!, R.color.lightish_blue)


        val lineData = LineData(lineDataSet)

        // 여기에 라인 데이터의 텍스트 컬러, 사이즈를 설정할 수 있다.

        lineData.setValueTextColor(ContextCompat.getColor(context!!, R.color.black))
        lineData.setValueTextSize(9f)
        report_chart_00.data = lineData
        report_chart_00.invalidate()
    }

    private fun mockMonthlyData(){
        val entries = mutableListOf<BarEntry>()
        entries.add(BarEntry(0f,30f))

        val set = BarDataSet(entries, "BarDataSet")
        val data = BarData(set)

        report_chart_01.data = data
        report_chart_01.setFitBars(true) // make the x-axis fit exactly all bars
        report_chart_01.invalidate() // refresh
    }
    */
}