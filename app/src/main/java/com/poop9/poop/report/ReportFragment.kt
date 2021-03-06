package com.poop9.poop.report

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.poop9.poop.R
import com.poop9.poop.data.api.PoopRepository
import com.poop9.poop.vo.RankData
import kotlinx.android.synthetic.main.fragment_report.*
import org.koin.android.ext.android.inject


class ReportFragment : Fragment() {

    private val repo: PoopRepository by inject()

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

        lifecycleScope.launchWhenStarted {
            try {
                loadChart()
                loadRankList()
                loadBestActivePoop()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private suspend fun loadChart() {
        report_title_daily_value.text = String.format("%s회", mockValueDaily())
        report_title_weekly_value.text = String.format("%s회", mockValueWeekly())
        report_title_monthly_value.text = String.format("%s회", mockValueMonthly())
    }

    private suspend fun loadRankList() {
        val rankAdapter = ReportRankAdapter(context!!, mockRankList())
        report_rank_list.adapter = rankAdapter
    }

    private fun loadBestActivePoop() {
        when (mockActivePoopDay()) {
            "MON" -> report_title_pattern_date.text = getString(R.string.report_mon)
            "TUE" -> report_title_pattern_date.text = getString(R.string.report_tue)
            "WED" -> report_title_pattern_date.text = getString(R.string.report_wed)
            "THU" -> report_title_pattern_date.text = getString(R.string.report_thu)
            "FRI" -> report_title_pattern_date.text = getString(R.string.report_fri)
            "SAT" -> report_title_pattern_date.text = getString(R.string.report_sat)
            "SUN" -> report_title_pattern_date.text = getString(R.string.report_sun)
        }
    }

    private suspend fun mockRankList(): List<RankData> {

//        val list = listOf(
//            RankData(1, "GROOT1", 100)
//            , RankData(2, "GROOT", 100)
//            , RankData(3, "GROOT", 100)
//            , RankData(4, "GROOT", 100)
//            , RankData(5, "GROOT", 100)
//            , RankData(6, "GROOT", 100)
//            , RankData(7, "GROOT", 100)
//            , RankData(8, "GROOT", 100)
//            , RankData(9, "GROOT", 100)
//            , RankData(10, "ALAN", 100)
//        )

        return repo.list().map { response ->
            RankData(response.num, response.nickname, response.count)
        }
    }

    private fun mockActivePoopDay(): String {
        return "SUN"
    }


    private suspend fun valueDaily(): Int {
        return repo.today().count
    }

    private suspend fun valueWeekly(): Int {
        return repo.week().count
    }

    private suspend fun valueMonthly(): Int {
        return repo.month().count
    }

    private suspend fun mockValueDaily(): Int {
        return repo.today().count
    }

    private suspend fun mockValueWeekly(): Int {
        return repo.week().count
    }

    private suspend fun mockValueMonthly(): Int {
        return repo.month().count
    }
}