package com.example.rxlearn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.rxlearn.databinding.ActivityMainBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.*
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import models.Student

class MainActivity : AppCompatActivity() {

    private lateinit var compositeDisposable: CompositeDisposable
    private lateinit var observable: Observable<Student>
    private lateinit var mBinding: ActivityMainBinding
    private val greeting: String = "Hello user"
    private val greetings = arrayOf("Hello A", "Hello B", "Hello C")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        observable = Observable.create { emitter ->
            Student.studenList.forEach {
                emitter.onNext(it)
            }

            emitter.onComplete()
        }
        compositeDisposable.add(
            observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getObserver())
        )


    }

    private fun getObserver(): DisposableObserver<Student>{
        return object : DisposableObserver<Student>(){
            override fun onNext(student: Student) {
                Log.i("Result", "student is active: ${student.isActive}")
            }

            override fun onError(error: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onComplete() {
                TODO("Not yet implemented")
            }

        }
    }
}