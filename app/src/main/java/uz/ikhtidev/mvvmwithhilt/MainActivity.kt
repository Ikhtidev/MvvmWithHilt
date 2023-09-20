package uz.ikhtidev.mvvmwithhilt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import uz.ikhtidev.mvvmwithhilt.databinding.ActivityMainBinding
import uz.ikhtidev.mvvmwithhilt.utils.network.Resource
import uz.ikhtidev.mvvmwithhilt.vm.MainViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {

            launch {

                mainViewModel.stateFlow
                    .collect {
                        when (it) {
                            is Resource.Failure -> {
                                binding.textView.text = it.throwable.message
                            }
                            is Resource.Loading -> {
                                binding.textView.text = getString(R.string.loading)
                            }
                            is Resource.Success -> {
                                binding.textView.text =
                                    String.format(
                                        getString(R.string.fetch_users),
                                        it.data.size.toString()
                                    )
                            }
                        }
                    }
            }
        }
    }
}