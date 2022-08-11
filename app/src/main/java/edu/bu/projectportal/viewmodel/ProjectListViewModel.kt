package edu.bu.projectportal.viewmodel

import android.app.Application
import androidx.lifecycle.*
import edu.bu.projectportal.datalayer.Project
import edu.bu.projectportal.ProjectPortalApplication
import edu.bu.projectportal.datalayer.ProjectDao
import edu.bu.projectportal.datalayer.ProjectRepository
import kotlinx.coroutines.launch
import java.util.concurrent.Executors


class ProjectListViewModel(val repository: ProjectRepository): ViewModel() {
    // pass the projectportalApplication as a parameter
    // make sure to define the application name in the manifest file.

    private val _projectList: LiveData<List<Project>> = repository.allProjects.asLiveData()
    val projectList:LiveData<List<Project>>
        get() = _projectList

    fun getAllProjects(): LiveData<List<Project>> {
        return repository.allProjects.asLiveData()
    }

    fun insert(project: Project) = viewModelScope.launch {
        repository.insert(project)
    }

}

class ProjectListViewModelFactory(private val repository: ProjectRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProjectListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProjectListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}