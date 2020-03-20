import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { connect } from "react-redux";
import { fetchAllGenres } from "../../store/actions";
import { fetchAllLanguages } from "../../store/actions";
import { fetchAllProductionCompanies } from "../../store/actions";
import { addGenrePreference } from "../../store/actions";
import { addLanguagePreference } from "../../store/actions";
import { addCompanyPreference } from "../../store/actions";
import { deleteGenrePreference } from "../../store/actions";
import { deleteLanguagePreference } from "../../store/actions";
import { deleteCompanyPreference } from "../../store/actions";
import Loader from "../../components/Loader";
import "./index.scss";
import { WithContext as ReactTags } from "react-tag-input";
import texts from "../../texts.json";

const ProfilePage = ({
  user,
  suggestions,
  promise,
  fetchAllGenres,
  fetchAllLanguages,
  fetchAllProductionCompanies,
  addGenrePreference,
  addLanguagePreference,
  addCompanyPreference,
  deleteGenrePreference,
  deleteLanguagePreference,
  deleteCompanyPreference
}) => {
  const delimiters = [];
  const { genre, language, company } = user.preferences;
  const [dropDown, setDropDownState] = useState({
    genre: false,
    language: false,
    company: false
  });

  useEffect(() => {
    if ((!suggestions.genre || suggestions.genre.length === 0) && user.email)
      fetchAllGenres();
    if (
      (!suggestions.language || suggestions.language.length === 0) &&
      user.email
    )
      fetchAllLanguages();
    if (suggestions.isCompanyLoaded === false && user.email) {
      suggestions.isCompanyLoaded = true;
      if (!suggestions.company || suggestions.company.length === 0)
        fetchAllProductionCompanies();
    }
  });

  const handleGenreAddition = tag => {
    const payload = {
      _id: tag._id,
      id: Number(tag.id),
      name: tag.text
    };
    addGenrePreference(user.email, payload);
  };

  const handleLanguageAddition = tag => {
    const payload = {
      _id: tag._id,
      iso_639_1: tag.iso_639_1,
      name: tag.text,
      languageName: tag.languageName
    };
    addLanguagePreference(user.email, payload);
  };

  const handleCompanyAddition = tag => {
    const payload = {
      _id: tag._id,
      id: parseInt(tag.id),
      name: tag.text
    };
    addCompanyPreference(user.email, payload);
  };

  const handleGenreDeletion = index => {
    const selectedGenre = genre[index];
    const payload = {
      _id: selectedGenre._id,
      id: parseInt(selectedGenre.id),
      name: selectedGenre.text
    };
    deleteGenrePreference(user.email, payload);
  };

  const handleLanguageDeletion = index => {
    const selectedLanguage = language[index];
    const payload = {
      _id: selectedLanguage._id,
      iso_639_1: selectedLanguage.iso_639_1,
      name: selectedLanguage.text,
      languageName: selectedLanguage.languageName
    };
    deleteLanguagePreference(user.email, payload);
  };

  const handleCompanyDeletion = index => {
    const selectedLanguage = company[index];
    const payload = {
      _id: selectedLanguage._id,
      id: parseInt(selectedLanguage.id),
      name: selectedLanguage.text
    };
    deleteCompanyPreference(user.email, payload);
  };

  const shouldRenderGenreSuggestions = query => {
    return dropDown.genre;
  };

  const shouldRenderLanguageSuggestions = query => {
    return dropDown.language;
  };

  const shouldRenderCompanySuggestions = query => {
    return dropDown.company;
  };

  const handleGenreInputFocus = inputText => {
    let dropDownStateCopy = {
      genre: true,
      language: false,
      company: false
    };
    setDropDownState(dropDownStateCopy);
  };

  const handleLanguageInputFocus = inputText => {
    let dropDownStateCopy = {
      genre: false,
      language: true,
      company: false
    };
    setDropDownState(dropDownStateCopy);
  };

  const handleCompanyInputFocus = inputText => {
    let dropDownStateCopy = {
      genre: false,
      language: false,
      company: true
    };
    setDropDownState(dropDownStateCopy);
  };

  const handleInputBlur = () => {
    let dropDownStateCopy = {
      genre: false,
      language: false,
      company: false
    };
    setDropDownState(dropDownStateCopy);
  };

  return promise.isPending ? (
    <Loader />
  ) : (
    <div className="profilepage-container">
      <Link to={"/dashboard"}>
        <button type="button" className="btn primary back-btn">
          {texts.backToHomeMessage}
        </button>
      </Link>
      <h1 className="page-title profile-header">{texts.profileHeader}</h1>
      <img src={user.picture} alt={texts.profileHeader} />
      <div className="basic-information">
        <h3 className="page-title user-name">{user.name}</h3>
        <p className="page-title">{user.email}</p>
        <h3 className="page-title preferences-header">{texts.preferences}</h3>
        <div className="preference-block">
          <p className="page-title">{texts.genre}:</p>
          <ReactTags
            autofocus={false}
            tags={genre}
            placeholder={texts.selectGenre}
            allowDragDrop={false}
            suggestions={suggestions.filteredGenre}
            handleDelete={handleGenreDeletion}
            handleAddition={handleGenreAddition}
            handleInputFocus={handleGenreInputFocus}
            handleInputBlur={handleInputBlur}
            shouldRenderSuggestions={shouldRenderGenreSuggestions}
            delimiters={delimiters}
          />
        </div>

        <div className="preference-block">
          <p className="page-title">{texts.language}:</p>
          <ReactTags
            autofocus={false}
            tags={language}
            placeholder={texts.selectLanguage}
            allowDragDrop={false}
            suggestions={suggestions.filteredLanguage}
            handleDelete={handleLanguageDeletion}
            handleAddition={handleLanguageAddition}
            handleInputFocus={handleLanguageInputFocus}
            handleInputBlur={handleInputBlur}
            shouldRenderSuggestions={shouldRenderLanguageSuggestions}
            delimiters={delimiters}
          />
        </div>

        <div className="preference-block">
          <p className="page-title">{texts.productionCompanies}:</p>
          <ReactTags
            autofocus={false}
            tags={company}
            placeholder={texts.selectCompany}
            allowDragDrop={false}
            suggestions={suggestions.company}
            handleDelete={handleCompanyDeletion}
            handleAddition={handleCompanyAddition}
            handleInputFocus={handleCompanyInputFocus}
            handleInputBlur={handleInputBlur}
            shouldRenderSuggestions={shouldRenderCompanySuggestions}
            delimiters={delimiters}
          />
        </div>
      </div>
    </div>
  );
};

const mapStateToProps = ({ user, suggestions, promise }) => ({
  user,
  suggestions,
  promise
});

const mapDispatchToProps = dispatch => ({
  fetchAllGenres: payload => dispatch(fetchAllGenres(payload)),
  fetchAllLanguages: payload => dispatch(fetchAllLanguages(payload)),
  fetchAllProductionCompanies: payload =>
    dispatch(fetchAllProductionCompanies(payload)),
  addGenrePreference: (email, payload) =>
    dispatch(addGenrePreference(email, payload)),
  addLanguagePreference: (email, payload) =>
    dispatch(addLanguagePreference(email, payload)),
  addCompanyPreference: (email, payload) =>
    dispatch(addCompanyPreference(email, payload)),
  deleteGenrePreference: (email, payload) =>
    dispatch(deleteGenrePreference(email, payload)),
  deleteLanguagePreference: (email, payload) =>
    dispatch(deleteLanguagePreference(email, payload)),
  deleteCompanyPreference: (email, payload) =>
    dispatch(deleteCompanyPreference(email, payload))
});

export { ProfilePage };

export default connect(mapStateToProps, mapDispatchToProps)(ProfilePage);
