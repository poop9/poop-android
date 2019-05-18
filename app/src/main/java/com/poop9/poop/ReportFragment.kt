package com.poop9.poop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.android.synthetic.main.fragment_report.*
import java.util.*


class ReportFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_report, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadChart()
    }

    private fun loadChart() {
        testChart()
    }

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

}