package com.example.qctmanagement.ui.wizard;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.qctmanagement.R;
import com.example.qctmanagement.model.WizardModel;

import java.util.ArrayList;
import java.util.List;

public class WizardViewModel extends ViewModel {
    private MutableLiveData<List<WizardModel>>  wizardViewModelData;

    public WizardViewModel() {
        wizardViewModelData= new MutableLiveData<>();

        getDataWizard();
    }

    private void getDataWizard() {
        List<WizardModel> models= new ArrayList<>();
        models.add(new WizardModel("Title 1","Description 1", R.drawable.img_wizard_1));
        models.add(new WizardModel("Title 2","Description 2", R.drawable.img_wizard_1));
        models.add(new WizardModel("Title 3","Description 3", R.drawable.img_wizard_1));
        models.add(new WizardModel("Title 4","Description 4", R.drawable.img_wizard_1));

        wizardViewModelData.setValue(models);
    }

    public MutableLiveData<List<WizardModel>> getWizardViewModelData() {
        return wizardViewModelData;
    }
}