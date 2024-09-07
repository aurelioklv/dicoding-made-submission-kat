package com.aurelioklv.kat.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.aurelioklv.kat.R
import com.aurelioklv.kat.core.data.local.preferences.AppTheme
import com.aurelioklv.kat.databinding.FragmentSettingsBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import java.util.Locale

class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SettingsViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.clTheme.setOnClickListener {
            showDialog()
        }

        // Observe theme changes
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.appTheme.observe(viewLifecycleOwner) { theme ->
                binding.tvDescription.text = theme.name.lowercase()
                    .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
                applyTheme(theme)
            }
        }
    }

    private fun showDialog() {
        val theme = viewModel.appTheme.value ?: AppTheme.SYSTEM
        val themeOptions = arrayOf("System Default", "Light", "Dark")
        var checkedIdx = theme.ordinal

        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.select_theme))
            .setSingleChoiceItems(themeOptions, checkedIdx) { _, which ->
                checkedIdx = which
            }
            .setPositiveButton(getString(R.string.ok)) { dialog, _ ->
                val newTheme = AppTheme.getThemeFromOrdinal(checkedIdx) ?: AppTheme.SYSTEM
                viewModel.updateTheme(newTheme)
                dialog.dismiss()
            }
            .setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun applyTheme(theme: AppTheme) {
        when (theme) {
            AppTheme.LIGHT -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            AppTheme.DARK -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            AppTheme.SYSTEM -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}