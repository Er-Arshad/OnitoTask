package com.onito.task.entity;




public class Movies {
	    
	    private String tconst;
	    private String titleType;
	    private String primaryTitle;
	    private int runtimeMinutes;
	    private String genres;
	    
		public Movies() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Movies(String tconst, String titleType, String primaryTitle, int runtimeMinutes, String genres) {
			super();
			this.tconst = tconst;
			this.titleType = titleType;
			this.primaryTitle = primaryTitle;
			this.runtimeMinutes = runtimeMinutes;
			this.genres = genres;
		}

		public Movies(String string, String string2, int genreTotalVotes) {
			// TODO Auto-generated constructor stub
		}

		public String getTconst() {
			return tconst;
		}

		public void setTconst(String tconst) {
			this.tconst = tconst;
		}

		public String getTitleType() {
			return titleType;
		}

		public void setTitleType(String titleType) {
			this.titleType = titleType;
		}

		public String getPrimaryTitle() {
			return primaryTitle;
		}

		public void setPrimaryTitle(String primaryTitle) {
			this.primaryTitle = primaryTitle;
		}

		public int getRuntimeMinutes() {
			return runtimeMinutes;
		}

		public void setRuntimeMinutes(int runtimeMinutes) {
			this.runtimeMinutes = runtimeMinutes;
		}

		public String getGenres() {
			return genres;
		}

		public void setGenres(String genres) {
			this.genres = genres;
		}

		@Override
		public String toString() {
			return "movies [tconst=" + tconst + ", titleType=" + titleType + ", primaryTitle=" + primaryTitle
					+ ", runtimeMinutes=" + runtimeMinutes + ", genres=" + genres + "]";
		}

	    
	
}
