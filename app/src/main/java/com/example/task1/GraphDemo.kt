package com.example.task1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.BarGraphSeries
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries

class GraphDemo : AppCompatActivity() {

    private lateinit var graphview:GraphView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graph_demo)

        graphview=findViewById(R.id.graphview)

        val series:BarGraphSeries<DataPoint> = BarGraphSeries(arrayOf(
            DataPoint(0.0,1.0),
            DataPoint(1.0,3.0),
            DataPoint(2.0,4.0),
            DataPoint(3.0,9.0),
            DataPoint(4.0,6.0),
            DataPoint(5.0,3.0),
            DataPoint(6.0,6.0),
            DataPoint(7.0,1.0),
            DataPoint(8.0,2.0)
        ))

        graphview.animate()
        graphview.viewport.isScrollable=true
        graphview.viewport.isScalable=true
        graphview.viewport.setScalableY(true)
        graphview.viewport.setScrollableY(true)
        series.color=R.color.primary

        graphview.addSeries(series)
    }
}