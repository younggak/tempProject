import pandas as pd
from com.chaquo.python import Python
import pickle
from sklearn.model_selection import train_test_split
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.feature_extraction.text import TfidfTransformer
from sklearn.naive_bayes import MultinomialNB
from os.path import dirname, join

def main():
  data = join(dirname(__file__),"text.txt")
  data = pd.read_csv(data)
  X_data = data['content']
  y_data = data['label']

  X_train, X_test, y_train, y_test = train_test_split(X_data, y_data, test_size=0.3, random_state=1234)
  count_vect = CountVectorizer()
  X_train_counts = count_vect.fit_transform(X_train)
  tfidf_transformer = TfidfTransformer()
  X_train_tfidf = tfidf_transformer.fit_transform(X_train_counts)
  clf = MultinomialNB().fit(X_train_tfidf, y_train)

  dir = str(Python.getPlatform().getApplication().getFilesDir())
  filename = join(dirname(dir),"model.pickle")
  with open(filename,'wb') as fin:
    pickle.dump(clf,fin)

  filename2 = join(dirname(dir),"count_vect.pickle")
  with open(filename2,'wb') as fin2:
    pickle.dump(count_vect,fin2)
