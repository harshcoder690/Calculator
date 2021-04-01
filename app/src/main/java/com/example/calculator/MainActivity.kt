package com.example.calculator

import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.security.AccessController.getContext


class MainActivity : AppCompatActivity() {

    var numbers= arrayListOf<Double>()
    var operators=arrayListOf<Char>()
    var Currno:String =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        buttonclearall.setOnClickListener{
            tvoutput.text=""
            tvinput.text=""
            numbers = arrayListOf();
            operators = arrayListOf();
            Currno=""
        }
        buttoncalc.setOnClickListener{
            val items: ArrayList<HashMap<String, Any>>
            val pm: PackageManager
            val packs: List<PackageInfo>

            // initialise From Oncreate if you want

            // initialise From Oncreate if you want
            items = ArrayList()
            pm = packageManager
            packs = pm.getInstalledPackages(0)
            for (pi in packs) {
                val map = HashMap<String, Any>()
                map["appName"] = pi.applicationInfo.loadLabel(pm)
                map["packageName"] = pi.packageName
                items.add(map)
            }
            fun opencalculator() {
                var d = 0
                if (items.size >= 1) {
                    var j = 0
                    j = 0
                    while (j < items.size) {
                        val AppName:String = items.get(j).get("appName").toString()
                        // Log.w("Name",""+AppName);
                        if (AppName.equals("Calculator")) {
                            d = j
                            break
                        }
                        j++
                    }
                    val packageName = items.get(d).get("packageName") as String
                    val i: Intent? = pm.getLaunchIntentForPackage(packageName)
                    if (i != null) {
                        Toast.makeText(this, "STARTING", Toast.LENGTH_SHORT).show()
                        startActivity(i)
                    } else {
                        Toast.makeText(this, "SORRY I CANT OPEN CALCULATOR :(", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "SORRY I CANT START CALCULATOR :(", Toast.LENGTH_SHORT).show()
                }
            }
            opencalculator();

        }
        buttonpercent.setOnClickListener{
            val exp=tvinput.text.toString()
            if(exp =="0"){
                tvinput.text="%"
            }
            tvinput.text=exp+"%"
            updatedisplay('%')
        }
        button0.setOnClickListener{
            val exp=tvinput.text.toString()
            if(tvinput.text==""){
                return@setOnClickListener
            }
            tvinput.text=exp+"0"

            updatedisplay('0')
        }

        button1.setOnClickListener{
            val exp=tvinput.text.toString()
            tvinput.text=exp+"1"

            updatedisplay('1')

        }
        button2.setOnClickListener{
            val exp=tvinput.text.toString()
            tvinput.text=exp+"2"

            updatedisplay('2')
        }
        button3.setOnClickListener{
            val exp=tvinput.text.toString()
            tvinput.text=exp+"3"

            updatedisplay('3')
        }
        button4.setOnClickListener{
            val exp=tvinput.text.toString()
            tvinput.text=exp+"4"

            updatedisplay('4')
        }
        button5.setOnClickListener{
            val exp=tvinput.text.toString()
            tvinput.text=exp+"5"


            updatedisplay('5')
        }
        button6.setOnClickListener{
            val exp=tvinput.text.toString()
            tvinput.text=exp+"6"

            updatedisplay('6')
        }
        button7.setOnClickListener{
            val exp=tvinput.text.toString()
            tvinput.text=exp+"7"

            updatedisplay('7')
        }
        button8.setOnClickListener{
            val exp=tvinput.text.toString()
            tvinput.text=exp+"8"

            updatedisplay('8')
        }
        button9.setOnClickListener{
            val exp=tvinput.text.toString()
            tvinput.text=exp+"9"

            updatedisplay('9')
        }
        buttonmultiply.setOnClickListener{
            val exp=tvinput.text.toString()
            tvinput.text=exp+"x"
            updatedisplay('x')
        }
        buttonplus.setOnClickListener{
            val exp=tvinput.text.toString()
            tvinput.text=exp+"+"
            updatedisplay('+')
        }
        buttonsubtract.setOnClickListener{
            val exp=tvinput.text.toString()
            tvinput.text=exp+"-"
            updatedisplay('-')
        }
        buttondivide.setOnClickListener{
            val exp=tvinput.text.toString()
            tvinput.text=exp+"/"
            updatedisplay('/')
        }
        button00.setOnClickListener{
            val exp=tvinput.text.toString()
            if(tvinput.text==""){
                return@setOnClickListener
            }
            tvinput.text=exp+"00"
            updatedisplay('&')
        }
        buttondot.setOnClickListener{
            val exp=tvinput.text.toString()
            if(tvinput.text==""){
                tvinput.text="0."
            }else {
                tvinput.text = exp + "."
            }

            updatedisplay('.')
        }
        buttonequals.setOnClickListener{
            if (Currno.equals("")) {
                operators.removeAt(operators.size - 1);
            }
            else {
                val al:Double= Currno.toDouble();
                numbers.add(al);
            }
            var i=0
            while(i<operators.size){

                 if (operators.get(i) == '/') {
                    if (numbers.get(i + 1) == 0.toDouble()) {
                        tvoutput.setText("invalid");
                        return@setOnClickListener
                    }
                    numbers.set(i, numbers.get(i) / numbers.get(i + 1));
                    numbers.removeAt(i + 1);
                    operators.removeAt(i);
                     i--
                }
                 else if (operators.get(i) == 'x') {
                    numbers.set(i, numbers.get(i) * numbers.get(i + 1));
                     numbers.removeAt(i + 1);
                     operators.removeAt(i);
                     i--;
                }
                i++
            }

            var ans:Double = numbers.get(0)
            var j = 1
            while(j < numbers.size) {
                if (operators.get(j - 1) == '+') {
                    ans += numbers.get(j);
                } else if (operators.get(j - 1) == '-') {
                    ans -= numbers.get(j);
                }
                j++
            }

            var Ans:String = ans.toString()
            var decimalPos = Ans.indexOf('.')
            if(Ans.lastIndexOf('E')==-1 && Ans.substring(decimalPos + 1).toLong() == 0L){
                Ans = Ans.substring(0, decimalPos);

            }
            if(Ans.substring(decimalPos-1).toLong()==0L){
                Ans="0."+Ans;
            }



            tvoutput.isEnabled=true;
            tvoutput.setText(Ans);
            Currno = Ans;
            numbers = arrayListOf();
            operators = arrayListOf();


        }



    }

    private fun updatedisplay(char: Char) {
        if(char>='0' && char<='9'){
            Currno+=char
        }
        else if(char=='&'){
            Currno+="00"
        }else if(char=='.'){
            if(Currno.length>0 && Currno.indexOf('.')==-1) {
                if(tvinput.text==""){
                    Currno=Currno+"0.";
                }else{
                    Currno += char;
                }

            }
        }

        else if(char=='/' ||char=='x' || char=='+' || char=='-' || char=='%' ){
            val num:Double=Currno.toDouble()
            numbers.add(num)
            Currno=""
            operators.add(char)

        }

    }



}




